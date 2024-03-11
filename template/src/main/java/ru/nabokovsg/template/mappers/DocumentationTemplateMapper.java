package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.template.models.DocumentationTemplate;

@Mapper(componentModel = "spring")
public interface DocumentationTemplateMapper {

    @Mapping(source = "documentation", target = "value")
    @Mapping(target = "id", ignore = true)
    DocumentationTemplate mapToDocumentationTemplate(String documentation);
}