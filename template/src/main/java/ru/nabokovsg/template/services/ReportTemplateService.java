package ru.nabokovsg.template.services;
import ru.nabokovsg.template.dto.report.ReportTemplateDto;
import ru.nabokovsg.template.models.AppendicesTemplate;
import ru.nabokovsg.template.models.PageTitleTemplate;
import ru.nabokovsg.template.models.ReportTemplate;
import ru.nabokovsg.template.models.SectionTemplate;

import java.util.Set;

public interface ReportTemplateService {

    ReportTemplateDto get(Long id);

    void addPageTitleTemplate(Long headerDocumentId, Long equipmentTypeId, PageTitleTemplate pageTitleTemplate);

    void addSectionTemplate(Long headerDocumentId, Long equipmentTypeId, Set<SectionTemplate> sections);

    ReportTemplate getByParam(Long headerDocumentId, Long equipmentTypeId);

    void addAppendices(Long equipmentTypeId, AppendicesTemplate appendices);
}