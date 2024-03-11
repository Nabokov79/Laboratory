package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.header.FullHeaderTemplateDto;
import ru.nabokovsg.template.dto.header.HeaderTemplateDto;
import ru.nabokovsg.template.models.HeaderTemplate;

public interface HeaderTemplateService {

    FullHeaderTemplateDto save(HeaderTemplateDto headerDto);

    FullHeaderTemplateDto update(HeaderTemplateDto headerDto);

    FullHeaderTemplateDto get(Long id);

    HeaderTemplate getByTypeDocument(Long headerDocumentId);

    void delete(Long id);
}