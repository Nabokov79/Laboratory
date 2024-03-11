package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.template.dto.columns.ColumnHeaderTemplateDto;
import ru.nabokovsg.template.models.ColumnHeaderTemplate;

@Mapper(componentModel = "spring")
public interface ColumnHeaderTemplateMapper {

    ColumnHeaderTemplate mapToColumnHeaderTemplates(ColumnHeaderTemplateDto templateDto);
}