package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.table.ResponseTableTemplateDto;
import ru.nabokovsg.template.dto.table.TableTemplateDto;

public interface TableTemplateService {

    ResponseTableTemplateDto save(TableTemplateDto tableDto);

    ResponseTableTemplateDto update(TableTemplateDto tableDto);

    ResponseTableTemplateDto get(Long id);

    void delete(Long id);
}