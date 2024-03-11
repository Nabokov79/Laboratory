package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.columns.ColumnHeaderTemplateDto;
import ru.nabokovsg.template.models.ColumnHeaderTemplate;

import java.util.List;

public interface ColumnHeaderTemplateService {

    List<ColumnHeaderTemplate> save(List<ColumnHeaderTemplateDto> columnHeaderTemplateDto);

    List<ColumnHeaderTemplate> update(List<ColumnHeaderTemplateDto> columnHeaderTemplateDto);
}