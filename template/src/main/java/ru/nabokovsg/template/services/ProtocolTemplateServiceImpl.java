package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.client.LNKClient;
import ru.nabokovsg.template.client.dto.HeaderDocumentDto;
import ru.nabokovsg.template.dto.protocol.ResponseProtocolTemplateDto;
import ru.nabokovsg.template.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.template.dto.protocol.ShortResponseProtocolTemplateDto;
import ru.nabokovsg.template.exceptions.NotFoundException;
import ru.nabokovsg.template.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.template.models.*;
import ru.nabokovsg.template.repository.ProtocolTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProtocolTemplateServiceImpl extends ConverterToEnum implements ProtocolTemplateService {

    private final ProtocolTemplateRepository repository;
    private final ProtocolTemplateMapper mapper;
    private final LNKClient client;
    private final HeaderTemplateService headerService;

    @Override
    public ShortResponseProtocolTemplateDto save(ProtocolTemplateDto protocolDto) {
        ProtocolTemplate template = repository.findByHeaderDocumentIdAndEquipmentTypeId(
                                                                                  protocolDto.getHeaderDocumentId()
                                                                                , protocolDto.getEquipmentTypeId());
        if (template == null) {
            HeaderDocumentDto headerDocumentDto = client.getHeaderDocument(protocolDto.getHeaderDocumentId());
            template = repository.save(
                    mapper.mapToProtocolTemplate(protocolDto
                                               , headerDocumentDto
                                               , headerService.getByTypeDocument(protocolDto.getHeaderDocumentId())));
        }
        return mapper.mapToShortProtocolTemplateDto(template);
    }

    @Override
    public ShortResponseProtocolTemplateDto update(ProtocolTemplateDto protocolDto) {
        if (repository.existsById(protocolDto.getId())) {
            HeaderDocumentDto headerDocumentDto = client.getHeaderDocument(protocolDto.getHeaderDocumentId());
            return mapper.mapToShortProtocolTemplateDto(
                    repository.save(
                            mapper.mapToProtocolTemplate(protocolDto
                                                , headerDocumentDto
                                                , headerService.getByTypeDocument(protocolDto.getHeaderDocumentId())))
            );
        }
        throw new NotFoundException(
                String.format("Protocol template by id=%s not found for update", protocolDto.getId())
        );
    }

    @Override
    public ResponseProtocolTemplateDto get(Long id) {
        return mapper.mapToFullProtocolTemplateDto(getById(id));
    }

    @Override
    public List<ShortResponseProtocolTemplateDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToShortProtocolTemplateDto)
                         .toList();
    }

    @Override
    public void addTable(Long id, TableTemplate table) {
        ProtocolTemplate protocol = getById(id);
        protocol.getTables().add(table);
        repository.save(protocol);
    }

    @Override
    public void addSubsection(Long id, SubsectionTemplate subsection) {
        ProtocolTemplate protocol = getById(id);
        protocol.getSubsections().add(subsection);
        repository.save(protocol);
    }


    @Override
    public void addConclusion(Long headerDocumentId, ConclusionTemplate conclusion) {
        ProtocolTemplate protocol = repository.findByHeaderDocumentId(headerDocumentId);
        if (protocol != null) {
            protocol.setConclusions(conclusion);
            repository.save(protocol);
        }
    }

    @Override
    public void addAppendices(Long equipmentTypeId, AppendicesTemplate appendices) {
        ProtocolTemplate protocol = repository.findByEquipmentTypeId(equipmentTypeId);
        if (protocol != null) {
            protocol.getAppendices().add(appendices);
            repository.save(protocol);
        }
    }

    public ProtocolTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Protocol template with id=%s not found", id)));
    }
}