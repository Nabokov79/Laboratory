package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.template.dto.section.FullSectionTemplateDto;
import ru.nabokovsg.template.dto.section.SectionTemplateDto;
import ru.nabokovsg.template.dto.section.ShortSectionTemplateDto;
import ru.nabokovsg.template.models.SectionTemplate;

@Mapper(componentModel = "spring")
public interface SectionTemplateMapper {

    SectionTemplate mapToSectionTemplate(SectionTemplateDto sectionDto);

    FullSectionTemplateDto mapToFullSectionTemplateDto(SectionTemplate section);

    ShortSectionTemplateDto mapToShortSectionTemplateDto(SectionTemplate section);
}