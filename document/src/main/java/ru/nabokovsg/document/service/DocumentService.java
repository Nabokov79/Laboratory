package ru.nabokovsg.document.service;

import ru.nabokovsg.document.dto.document.TaskJournalDto;
import ru.nabokovsg.document.dto.document.DocumentSearchParam;
import ru.nabokovsg.document.dto.document.ResponseDocumentDto;
import ru.nabokovsg.document.models.Document;
import ru.nabokovsg.document.models.enums.DocumentStatus;

import java.util.List;

public interface DocumentService {

    Long save(TaskJournalDto taskJournalDto);
    ResponseDocumentDto get(Long id);
    List<ResponseDocumentDto> getAll(DocumentSearchParam param);

    Document getById(Long id);

    void updateStatus(Long documentId, DocumentStatus status);
}