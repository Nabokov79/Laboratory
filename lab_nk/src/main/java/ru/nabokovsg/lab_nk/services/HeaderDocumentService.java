package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.headerDocument.HeaderDocumentDto;
import ru.nabokovsg.lab_nk.dto.headerDocument.FullHeaderDocumentDto;
import ru.nabokovsg.lab_nk.models.HeaderDocument;

import java.util.List;

public interface HeaderDocumentService {

    FullHeaderDocumentDto save(HeaderDocumentDto headerDocumentDto);

    FullHeaderDocumentDto update(HeaderDocumentDto headerDocumentDto);

    FullHeaderDocumentDto get(Long id);

    List<FullHeaderDocumentDto> getAll();

    void delete(Long id);

    HeaderDocument getById(Long id);
}