package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.headerDocument.HeaderDocumentDto;
import ru.nabokovsg.lab_nk.dto.headerDocument.ResponseHeaderDocumentDto;
import ru.nabokovsg.lab_nk.models.HeaderDocument;

import java.util.List;

public interface HeaderDocumentService {

    ResponseHeaderDocumentDto save(HeaderDocumentDto headerDocumentDto);

    ResponseHeaderDocumentDto update(HeaderDocumentDto headerDocumentDto);

    ResponseHeaderDocumentDto get(Long id);

    List<ResponseHeaderDocumentDto> getAll();

    void delete(Long id);

    HeaderDocument getById(Long id);
}