package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.template.client.dto.HeaderDocumentDto;
import ru.nabokovsg.template.dto.pageTitle.ResponsePageTitleTemplateDto;
import ru.nabokovsg.template.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.template.dto.pageTitle.ShortResponsePageTitleTemplateDto;
import ru.nabokovsg.template.models.HeaderTemplate;
import ru.nabokovsg.template.models.PageTitleTemplate;

@Mapper(componentModel = "spring")
public interface PageTitleTemplateMapper {

    @Mapping(source = "header", target = "header")
    @Mapping(source = "headerDocumentDto.title", target = "title")
    @Mapping(source = "headerDocumentDto.heading", target = "heading")
    @Mapping(source = "pageTitleDto.equipment", target = "equipment")
    @Mapping(source = "pageTitleDto.installationLocation", target = "installationLocation")
    @Mapping(source = "pageTitleDto.address", target = "address")
    @Mapping(source = "post", target = "post")
    @Mapping(source = "employee", target = "employee")
    @Mapping(source = "pageTitleDto.city", target = "city")
    @Mapping(source = "pageTitleDto.id", target = "id")
    PageTitleTemplate mapToPageTitleTemplate(PageTitleTemplateDto pageTitleDto, HeaderTemplate header
            , HeaderDocumentDto headerDocumentDto, String post, String employee);

    ResponsePageTitleTemplateDto mapToFullPageTitleTemplateDto(PageTitleTemplate pageTitle);

    ShortResponsePageTitleTemplateDto mapToShortPageTitleTemplateDto(PageTitleTemplate pageTitle);
}