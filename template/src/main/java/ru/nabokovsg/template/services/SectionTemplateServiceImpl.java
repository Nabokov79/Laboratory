package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.dto.section.SectionDataTemplateDto;
import ru.nabokovsg.template.dto.section.FullSectionTemplateDto;
import ru.nabokovsg.template.dto.section.SectionTemplateDto;
import ru.nabokovsg.template.dto.section.ShortSectionTemplateDto;
import ru.nabokovsg.template.exceptions.NotFoundException;
import ru.nabokovsg.template.mappers.SectionTemplateMapper;
import ru.nabokovsg.template.models.ProtocolReportTemplate;
import ru.nabokovsg.template.models.SectionTemplate;
import ru.nabokovsg.template.models.SubsectionTemplate;
import ru.nabokovsg.template.repository.SectionTemplateRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionTemplateServiceImpl implements SectionTemplateService {

    private final SectionTemplateRepository repository;
    private final SectionTemplateMapper mapper;
    private final ReportTemplateService reportService;

    @Override
    public List<ShortSectionTemplateDto> save(SectionDataTemplateDto sectionsDataDto) {
        Set<SectionTemplate> sections = repository.findAllSection(sectionsDataDto.getHeaderDocumentId(), sectionsDataDto.getEquipmentTypeId());
        List<SectionTemplateDto> sectionsDto = new ArrayList<>();
        if (!sections.isEmpty()) {
            List<String> sectionNames = sections.stream().map(SectionTemplate::getSectionName).toList();
            sectionsDto = sectionsDataDto.getSections().stream().filter(s -> !sectionNames.contains(s.getSectionName())).toList();
        }
        if (sectionsDto.isEmpty()) {
            return sections.stream()
                             .map(mapper::mapToShortSectionTemplateDto)
                             .sorted(Comparator.comparing(ShortSectionTemplateDto::getId))
                             .toList();
        }
        sections.addAll(repository.saveAll(sectionsDto.stream().map(mapper::mapToSectionTemplate).toList()));
        reportService.addSectionTemplate(sectionsDataDto.getHeaderDocumentId(), sectionsDataDto.getEquipmentTypeId(), sections);
        return sections.stream()
                .map(mapper::mapToShortSectionTemplateDto)
                .sorted(Comparator.comparing(ShortSectionTemplateDto::getId))
                .toList();
    }

    @Override
    public List<ShortSectionTemplateDto> update(List<SectionTemplateDto> sectionsDto) {
        validateIds(sectionsDto.stream().map(SectionTemplateDto::getId).toList());
        return repository.saveAll(sectionsDto.stream()
                        .map(mapper::mapToSectionTemplate)
                        .toList())
                .stream()
                .map(mapper::mapToShortSectionTemplateDto)
                .toList();
    }

    @Override
    public FullSectionTemplateDto get(Long id) {
        return mapper.mapToFullSectionTemplateDto(getById(id));
    }

    @Override
    public List<ShortSectionTemplateDto> getAll(Long id) {
        return repository.findAllByReportIdOrderById(id)
                         .stream()
                         .map(mapper::mapToShortSectionTemplateDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("SectionTemplate with id=%s not found for delete", id));
    }

    @Override
    public void addProtocol(Long id, ProtocolReportTemplate protocol) {
        SectionTemplate section = getById(id);
        section.getProtocols().add(protocol);
        repository.save(section);
    }


    @Override
    public void addSubsection(Long id, SubsectionTemplate subsection) {
        SectionTemplate section = getById(id);
        section.getSubsections().add(subsection);
        repository.save(section);
    }

    private SectionTemplate getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        String.format(String.format("Section template with id= %s not found", id))
                )
        );
    }

    private void validateIds(List<Long> ids) {
        Map<Long, SectionTemplate> sections = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SectionTemplate::getId, m -> m));
        if (sections.size() != ids.size() || sections.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(sections.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Section template with ids= %s not found", ids));
        }
    }
}
