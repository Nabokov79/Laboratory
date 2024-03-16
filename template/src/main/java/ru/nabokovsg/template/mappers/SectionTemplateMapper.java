package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.template.dto.section.ResponseSectionTemplateDto;
import ru.nabokovsg.template.dto.section.SectionTemplateDto;
import ru.nabokovsg.template.dto.section.ShortResponseSectionTemplateDto;
import ru.nabokovsg.template.models.SectionTemplate;

@Mapper(componentModel = "spring")
public interface SectionTemplateMapper {

    SectionTemplate mapToSectionTemplate(SectionTemplateDto sectionDto);

    ResponseSectionTemplateDto mapToFullSectionTemplateDto(SectionTemplate section);

    ShortResponseSectionTemplateDto mapToShortSectionTemplateDto(SectionTemplate section);
}