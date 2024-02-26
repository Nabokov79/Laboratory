package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.headerDocument.HeaderDocumentDto;
import ru.nabokovsg.lab_nk.dto.headerDocument.FullHeaderDocumentDto;
import java.util.List;

public interface HeaderDocumentService {

    FullHeaderDocumentDto save(HeaderDocumentDto headerDocumentDto);

    FullHeaderDocumentDto update(HeaderDocumentDto headerDocumentDto);

    List<FullHeaderDocumentDto> getAll();

    void delete(Long id);
}