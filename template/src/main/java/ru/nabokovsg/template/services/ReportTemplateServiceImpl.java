package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.dto.report.ResponseReportTemplateDto;
import ru.nabokovsg.template.exceptions.NotFoundException;
import ru.nabokovsg.template.mappers.ReportTemplateMapper;
import ru.nabokovsg.template.models.AppendicesTemplate;
import ru.nabokovsg.template.models.PageTitleTemplate;
import ru.nabokovsg.template.models.ReportTemplate;
import ru.nabokovsg.template.models.SectionTemplate;
import ru.nabokovsg.template.repository.ReportTemplateRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository repository;
    private final ReportTemplateMapper mapper;

    @Override
    public ResponseReportTemplateDto get(Long id) {
        return mapper.mapToReportTemplateDto(
                repository.findById(id)
                  .orElseThrow(() -> new NotFoundException(String.format("ReportTemplateDto with id=%s not found", id)))
        );
    }

    @Override
    public void addPageTitleTemplate(Long headerDocumentId, Long equipmentTypeId, PageTitleTemplate pageTitleTemplate) {
        repository.save(
                mapper.mapPageTitleWithReportTemplate(getByParam(headerDocumentId, equipmentTypeId)
                                                    , headerDocumentId
                                                    , equipmentTypeId
                                                    , pageTitleTemplate)
        );
    }

    @Override
    public void addSectionTemplate(Long headerDocumentId, Long equipmentTypeId, Set<SectionTemplate> sections) {
        repository.save(
                mapper.mapSectionTemplateWithReportTemplate(getByParam(headerDocumentId, equipmentTypeId)
                                                          , headerDocumentId
                                                          , equipmentTypeId
                                                          , sections)
        );
    }

    @Override
    public ReportTemplate getByParam(Long headerDocumentId, Long equipmentTypeId) {
        return repository.findByHeaderDocumentIdAndEquipmentTypeId(headerDocumentId, equipmentTypeId);
    }

    @Override
    public void addAppendices(Long equipmentTypeId, AppendicesTemplate appendices) {
        ReportTemplate reportTemplate = repository.findByEquipmentTypeId(equipmentTypeId);
        if (reportTemplate != null) {
            reportTemplate.getAppendices().add(appendices);
            repository.save(reportTemplate);
        }
    }
}