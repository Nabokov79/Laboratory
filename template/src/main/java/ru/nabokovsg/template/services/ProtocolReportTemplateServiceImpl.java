package ru.nabokovsg.template.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.client.LNKClient;
import ru.nabokovsg.template.dto.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.template.dto.protocolReport.FullProtocolReportTemplateDto;
import ru.nabokovsg.template.dto.protocolReport.ShortProtocolReportTemplateDto;
import ru.nabokovsg.template.exceptions.NotFoundException;
import ru.nabokovsg.template.mappers.ProtocolReportTemplateMapper;
import ru.nabokovsg.template.models.*;
import ru.nabokovsg.template.repository.ProtocolReportTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProtocolReportTemplateServiceImpl implements ProtocolReportTemplateService {

    private final ProtocolReportTemplateRepository repository;
    private final ProtocolReportTemplateMapper mapper;
    private final SectionTemplateService sectionService;
    private final EntityManager em;
    private final LNKClient client;

    @Override
    public ShortProtocolReportTemplateDto save(ProtocolReportTemplateDto protocolDto) {
        ProtocolReportTemplate protocol = getByPredicate(protocolDto.getHeaderDocumentId(), protocolDto.getSectionId());
        if (protocol == null) {
            protocol = repository.save(
                    mapper.mapToProtocolReportTemplate(protocolDto
                                                     , client.getHeaderDocument(protocolDto.getHeaderDocumentId()))
            );
            sectionService.addProtocol(protocolDto.getSectionId(), protocol);
        }
        return mapper.mapToShortProtocolReportTemplateDto(protocol);
    }

    @Override
    public ShortProtocolReportTemplateDto update(ProtocolReportTemplateDto protocolDto) {
        if (repository.existsById(protocolDto.getId())) {
            return mapper.mapToShortProtocolReportTemplateDto(
                    repository.save(
                            mapper.mapToProtocolReportTemplate(protocolDto
                                                        , client.getHeaderDocument(protocolDto.getHeaderDocumentId())))
            );
        }
        throw new NotFoundException(
                String.format("ProtocolReportTemplate with id=%s not found for update", protocolDto.getId())
        );
    }

    @Override
    public FullProtocolReportTemplateDto get(Long id) {
        return mapper.mapToFullProtocolReportTemplateDto(getById(id));
    }

    @Override
    public List<ShortProtocolReportTemplateDto> getAll(Long id) {
        return repository.findAllProtocols(id)
                         .stream()
                         .map(mapper::mapToShortProtocolReportTemplateDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void addTable(Long id, TableTemplate table) {
        ProtocolReportTemplate protocol = getById(id);
        protocol.getTables().add(table);
        repository.save(protocol);
    }

    @Override
    public void addSubsection(Long id, SubsectionTemplate subsection) {
        ProtocolReportTemplate protocol = getById(id);
        protocol.getSubsections().add(subsection);
        repository.save(protocol);
    }

    @Override
    public void addConclusion(Long headerDocumentId, ConclusionTemplate conclusion) {
        ProtocolReportTemplate protocol = repository.findByHeaderDocumentId(headerDocumentId);
        if (protocol.getConclusions() == null) {
            protocol.setConclusions(conclusion);
            repository.save(protocol);
        }
    }

    private ProtocolReportTemplate getByPredicate(Long headerDocumentId, Long sectionId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QProtocolReportTemplate.protocolReportTemplate.headerDocumentId.eq(headerDocumentId));
        booleanBuilder.and(QSectionTemplate.sectionTemplate.id.eq(sectionId));
        QProtocolReportTemplate protocolReportTemplate = QProtocolReportTemplate.protocolReportTemplate;
        return new JPAQueryFactory(em).from(protocolReportTemplate)
                .select(protocolReportTemplate)
                .where(booleanBuilder)
                .fetchOne();
    }

    private ProtocolReportTemplate getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Protocol report template with id=%s not found", id))
        );
    }
}