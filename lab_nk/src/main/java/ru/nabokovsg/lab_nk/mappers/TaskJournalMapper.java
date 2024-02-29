package ru.nabokovsg.lab_nk.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.lab_nk.dto.taskJournal.FullTaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TaskJournalDto;
import ru.nabokovsg.lab_nk.models.TasksJournal;

@Mapper(componentModel = "spring")
public interface TaskJournalMapper {

    TasksJournal mapToTaskJournal(TaskJournalDto taskJournalDto, String branch
                                , String building, String address, String equipment);

    FullTaskJournalDto mapToFullTasksJournalDto(TasksJournal tasksJournal);
}