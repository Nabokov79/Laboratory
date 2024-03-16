package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.template.dto.appendices.ResponseAppendicesTemplateDto;

public interface AppendicesTemplateService {

    ResponseAppendicesTemplateDto save(AppendicesTemplateDto appendicesDto);

    ResponseAppendicesTemplateDto update(AppendicesTemplateDto appendicesDto);

    void delete(Long id);
}