package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.taskJournal.ResponseTaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TaskSearchParameters;

import java.util.List;

public interface TaskJournalService {

    ResponseTaskJournalDto save(TaskJournalDto taskJournalDto);

    ResponseTaskJournalDto update(TaskJournalDto taskJournalDto);

    ResponseTaskJournalDto get(Long id);

    List<ResponseTaskJournalDto> getAll(TaskSearchParameters parameters);

    void delete(Long id);
}