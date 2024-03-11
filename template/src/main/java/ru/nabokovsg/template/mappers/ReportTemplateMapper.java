package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.template.dto.report.ReportTemplateDto;
import ru.nabokovsg.template.models.PageTitleTemplate;
import ru.nabokovsg.template.models.ReportTemplate;
import ru.nabokovsg.template.models.SectionTemplate;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ReportTemplateMapper {

    @Mapping(source = "headerDocumentId", target = "headerDocumentId")
    @Mapping(source = "equipmentTypeId", target = "equipmentTypeId")
    @Mapping(source = "pageTitleTemplate", target = "pageTitle")
    @Mapping(source = "reportTemplate.id", target = "id")
    ReportTemplate mapPageTitleWithReportTemplate(ReportTemplate reportTemplate
                                        , Long headerDocumentId
                                        , Long equipmentTypeId
                                        , PageTitleTemplate pageTitleTemplate);

    @Mapping(source = "headerDocumentId", target = "headerDocumentId")
    @Mapping(source = "equipmentTypeId", target = "equipmentTypeId")
    @Mapping(source = "sections", target = "sections")
    @Mapping(source = "reportTemplate.id", target = "id")
    ReportTemplate mapSectionTemplateWithReportTemplate(ReportTemplate reportTemplate
                                                      , Long headerDocumentId
                                                      , Long equipmentTypeId
                                                      , Set<SectionTemplate> sections);

    ReportTemplateDto mapToReportTemplateDto(ReportTemplate reportTemplate);
}