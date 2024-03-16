package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.documentation.DocumentationDto;
import ru.nabokovsg.lab_nk.dto.documentation.ResponseDocumentationDto;

import java.util.List;

public interface DocumentationService {

    ResponseDocumentationDto save(DocumentationDto documentationDto);

    ResponseDocumentationDto update(DocumentationDto documentationDto);

    List<ResponseDocumentationDto> getAll(List<Long> ids, String number, String title);

   void delete(Long id);
}