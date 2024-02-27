package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.documentation.DocumentationDto;
import ru.nabokovsg.lab_nk.dto.documentation.FullDocumentationDto;

import java.util.List;

public interface DocumentationService {

    FullDocumentationDto save(DocumentationDto documentationDto);

    FullDocumentationDto update(DocumentationDto documentationDto);

    List<FullDocumentationDto> getAll(List<Long> ids, String number, String title);

   void delete(Long id);
}