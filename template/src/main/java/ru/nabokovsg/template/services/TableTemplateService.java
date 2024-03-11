package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.table.FullTableTemplateDto;
import ru.nabokovsg.template.dto.table.TableTemplateDto;

public interface TableTemplateService {

    FullTableTemplateDto save(TableTemplateDto tableDto);

    FullTableTemplateDto update(TableTemplateDto tableDto);

    FullTableTemplateDto get(Long id);

    void delete(Long id);
}