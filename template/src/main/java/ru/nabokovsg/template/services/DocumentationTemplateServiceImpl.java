package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.client.LNKClient;
import ru.nabokovsg.template.mappers.DocumentationTemplateMapper;
import ru.nabokovsg.template.models.DocumentationTemplate;
import ru.nabokovsg.template.repository.DocumentationTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentationTemplateServiceImpl implements DocumentationTemplateService {

    private final DocumentationTemplateRepository repository;
    private final DocumentationTemplateMapper mapper;
    private final LNKClient client;
    private final StringBuilderService stringBuilderService;

    @Override
    public List<DocumentationTemplate> save(List<Long> ids) {
        return repository.saveAll(
                client.getDocumentations(ids)
                      .stream()
                      .map(s -> mapper.mapToDocumentationTemplate(stringBuilderService.buildFromDocumentation(s)))
                      .toList()
        );
    }
}