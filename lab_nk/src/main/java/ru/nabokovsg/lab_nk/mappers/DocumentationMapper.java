package ru.nabokovsg.lab_nk.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.lab_nk.dto.documentation.DocumentationDto;
import ru.nabokovsg.lab_nk.dto.documentation.ResponseDocumentationDto;
import ru.nabokovsg.lab_nk.models.Documentation;

@Mapper(componentModel = "spring")
public interface DocumentationMapper {

    Documentation mapToDocumentation(DocumentationDto documentationDto);

    ResponseDocumentationDto mapToFullDocumentationDto(Documentation documentation);
}