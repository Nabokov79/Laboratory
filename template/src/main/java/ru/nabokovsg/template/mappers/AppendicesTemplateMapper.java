package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.template.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.template.dto.appendices.ResponseAppendicesTemplateDto;
import ru.nabokovsg.template.models.AppendicesTemplate;

@Mapper(componentModel = "spring")
public interface AppendicesTemplateMapper {

    AppendicesTemplate mapToAppendicesTemplate(AppendicesTemplateDto appendicesDto);

    ResponseAppendicesTemplateDto mapToFullAppendicesTemplateDto(AppendicesTemplate appendices);
}