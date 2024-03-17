package ru.nabokovsg.lab_nk.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.lab_nk.dto.taskJournal.ResponseTaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TasksJournalDataDto;
import ru.nabokovsg.lab_nk.models.TasksJournal;

@Mapper(componentModel = "spring")
public interface TaskJournalMapper {

    TasksJournal mapToTaskJournal(TaskJournalDto taskJournalDto);

    ResponseTaskJournalDto mapToFullTasksJournalDto(TasksJournal tasksJournal);

    TasksJournalDataDto mapToTasksJournalDataDto(TasksJournal tasksJournal);
}