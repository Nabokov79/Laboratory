package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.template.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.template.dto.conclusion.FullConclusionTemplateDto;
import ru.nabokovsg.template.models.ConclusionTemplate;

@Mapper(componentModel = "spring")
public interface ConclusionTemplateMapper {

    ConclusionTemplate mapToConclusionTemplate(ConclusionTemplateDto conclusionDto);

    FullConclusionTemplateDto mapToFullConclusionTemplateDto(ConclusionTemplate conclusion);
}