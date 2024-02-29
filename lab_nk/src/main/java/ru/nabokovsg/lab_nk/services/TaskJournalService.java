package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.taskJournal.FullTaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TaskSearchParameters;

import java.util.List;

public interface TaskJournalService {

    FullTaskJournalDto save(TaskJournalDto taskJournalDto);

    FullTaskJournalDto update(TaskJournalDto taskJournalDto);

    FullTaskJournalDto get(Long id);

    List<FullTaskJournalDto> getAll(TaskSearchParameters parameters);

    void delete(Long id);
}