package ru.nabokovsg.document.service;

import ru.nabokovsg.document.dto.TaskJournalDto;
import ru.nabokovsg.document.dto.document.DocumentSearchParam;
import ru.nabokovsg.document.dto.document.FullDocumentDto;
import ru.nabokovsg.document.models.Document;

import java.util.List;

public interface DocumentService {

    Long save(TaskJournalDto taskJournalDto);
    FullDocumentDto get(Long id);
    List<FullDocumentDto> getAll(DocumentSearchParam param);

    Document getById(Long id);
}