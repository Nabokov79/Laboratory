package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.template.dto.subsection.DivisionDataDto;
import ru.nabokovsg.template.dto.subsection.ResponseSubsectionTemplateDto;
import ru.nabokovsg.template.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.template.models.SubsectionTemplate;

@Mapper(componentModel = "spring")
public interface SubsectionTemplateMapper {

    SubsectionTemplate mapToSubsectionTemplate(SubsectionTemplateDto subsectionDto);;

    DivisionDataDto mapToDivisionDataDto(SubsectionTemplateDto subsectionDto);

    ResponseSubsectionTemplateDto mapToFullSubsectionTemplateDto(SubsectionTemplate subsection);
}