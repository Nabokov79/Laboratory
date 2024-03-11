package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.client.CompanyClient;
import ru.nabokovsg.template.client.LNKClient;
import ru.nabokovsg.template.dto.subsection.DivisionDataDto;
import ru.nabokovsg.template.dto.subsection.FullSubsectionTemplateDto;
import ru.nabokovsg.template.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.template.exceptions.NotFoundException;
import ru.nabokovsg.template.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.template.models.SubsectionTemplate;
import ru.nabokovsg.template.models.TableTemplate;
import ru.nabokovsg.template.models.enums.TemplateType;
import ru.nabokovsg.template.repository.SubsectionTemplateRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl extends ConverterToEnum implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final MeasuringToolTemplateService measuringToolTemplateService;
    private final DocumentationTemplateService documentationTemplateService;
    private final SectionTemplateService sectionService;
    private final StringBuilderService stringBuilderService;
    private final LNKClient lnkClient;
    private final CompanyClient companyClient;
    private final ProtocolReportTemplateService protocolReportService;
    private final ProtocolTemplateService protocolService;

    @Override
    public FullSubsectionTemplateDto save(SubsectionTemplateDto subsectionDto) {
        TemplateType type = convertToTemplateType(subsectionDto.getTemplateType());
        SubsectionTemplate subsection = exists(type, subsectionDto.getTemplateId(), subsectionDto.getSubsectionName());
        if (subsection == null) {
            subsection = setSubsectionData(mapper.mapToSubsectionTemplate(subsectionDto), subsectionDto);
            switch (type) {
                case SECTION -> sectionService.addSubsection(subsectionDto.getTemplateId(), subsection);
                case PROTOCOL -> protocolService.addSubsection(subsectionDto.getTemplateId(), subsection);
                case PROTOCOL_REPORT -> protocolReportService.addSubsection(subsectionDto.getTemplateId(), subsection);
            }
        }
        return mapper.mapToFullSubsectionTemplateDto(subsection);
    }

    @Override
    public FullSubsectionTemplateDto update(SubsectionTemplateDto subsectionDto) {
        if (repository.existsById(subsectionDto.getId())) {
            return mapper.mapToFullSubsectionTemplateDto(setSubsectionData(
                                                    mapper.mapToSubsectionTemplate(subsectionDto), subsectionDto));
        }
        throw new NotFoundException(
                String.format("Subsection template with id=%s not found for update", subsectionDto.getId())
        );
    }

    @Override
    public FullSubsectionTemplateDto get(Long id) {
        return mapper.mapToFullSubsectionTemplateDto(getById(id));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("SubsectionTemplate with id=%s not found for delete", id));
    }

    private SubsectionTemplate setSubsectionData(SubsectionTemplate subsection, SubsectionTemplateDto subsectionDto) {
        switch (convertToSubsectionDataType(subsectionDto.getSubsectionDataType())) {
            case DOCUMENTATION -> setDocumentationData(subsection, subsectionDto.getDocumentationIds());
            case CERTIFICATE -> setCertificateEmployee(subsection, subsectionDto.getEmployeeId());
            case DIVISION -> setDivisionData(subsection, mapper.mapToDivisionDataDto(subsectionDto));
            case MEASURING_TOOL -> setMeasuringToolData(subsection, subsectionDto.getMeasuringToolIds());
        }
        return subsection;
    }

    @Override
    public void addTable(Long id, TableTemplate table) {
        SubsectionTemplate template = getById(id);
        if (template.getTable() == null) {
            template.setTable(table);
            repository.save(template);
        }
    }

    private void setDivisionData(SubsectionTemplate subsection, DivisionDataDto division) {
        if (division != null) {
           subsection.setDivisionData(stringBuilderService.buildFromDivision(
                   companyClient.getDivisionData(convertToDivisionType(division.getDivisionType())
                           , division.getDivisionId())
                   , division));
        }
    }

    private void setCertificateEmployee(SubsectionTemplate subsection, Long employeeId) {
        if (employeeId != null && employeeId > 0) {
            subsection.setCertificateEmployee(
                    stringBuilderService.buildFromEmployeeCertificate(lnkClient.getLaboratoryEmployee(employeeId))
            );
        }
    }

    private void setDocumentationData(SubsectionTemplate subsection, List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            subsection.setDocumentation(documentationTemplateService.save(ids));
        }
    }

    private void setMeasuringToolData(SubsectionTemplate subsection, List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            subsection.setMeasuringTools(measuringToolTemplateService.save(ids));
        }
    }

    public SubsectionTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Subsection template with id=%s not found", id))
                );
    }

    private SubsectionTemplate exists(TemplateType type, Long id, String subsectionName) {
        Set<SubsectionTemplate> subsections = new HashSet<>();
        switch (type) {
            case SECTION -> subsections = repository.findBySection(id);
            case PROTOCOL -> subsections = repository.findByProtocol(id);
            case PROTOCOL_REPORT -> subsections = repository.findByProtocolReport(id);
        }
        if (subsections.isEmpty()) {
            return null;
        }
        return subsections.stream()
                .collect(Collectors.toMap(SubsectionTemplate::getSubsectionName, s -> s))
                .get(subsectionName);
    }
}