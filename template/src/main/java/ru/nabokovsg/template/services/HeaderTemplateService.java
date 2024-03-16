package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.header.ResponseHeaderTemplateDto;
import ru.nabokovsg.template.dto.header.HeaderTemplateDto;
import ru.nabokovsg.template.models.HeaderTemplate;

public interface HeaderTemplateService {

    ResponseHeaderTemplateDto save(HeaderTemplateDto headerDto);

    ResponseHeaderTemplateDto update(HeaderTemplateDto headerDto);

    ResponseHeaderTemplateDto get(Long id);

    HeaderTemplate getByTypeDocument(Long headerDocumentId);

    void delete(Long id);
}