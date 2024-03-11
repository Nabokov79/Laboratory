package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.template.dto.appendices.FullAppendicesTemplateDto;

public interface AppendicesTemplateService {

    FullAppendicesTemplateDto save(AppendicesTemplateDto appendicesDto);

    FullAppendicesTemplateDto update(AppendicesTemplateDto appendicesDto);

    void delete(Long id);
}