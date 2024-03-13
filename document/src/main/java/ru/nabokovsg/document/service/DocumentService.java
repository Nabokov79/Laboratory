package ru.nabokovsg.document.service;

import ru.nabokovsg.document.dto.TaskJournalDto;
import ru.nabokovsg.document.dto.document.DocumentSearchParam;
import ru.nabokovsg.document.dto.document.FullDocumentDto;
import ru.nabokovsg.lab_nk.dto.document.DocumentSearchParam;
import ru.nabokovsg.lab_nk.dto.document.FullDocumentDto;
import ru.nabokovsg.lab_nk.models.Document;
import ru.nabokovsg.lab_nk.models.TasksJournal;

import java.util.List;

public interface DocumentService {

    Boolean save(TaskJournalDto taskJournalDto);
    FullDocumentDto get(Long id, String type);
    List<FullDocumentDto> getAll(DocumentSearchParam param);
    void create(TasksJournal task);

    Document getById(Long id);

    void validByTasksJournalId(Long id);
}