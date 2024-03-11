package ru.nabokovsg.template.services;

import ru.nabokovsg.template.models.DocumentationTemplate;

import java.util.List;

public interface DocumentationTemplateService {

    List<DocumentationTemplate> save(List<Long> ids);
}