package ru.nabokovsg.lab_nk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.lab_nk.dto.taskJournal.ResponseTaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TaskJournalDto;
import ru.nabokovsg.lab_nk.models.TasksJournal;

@Mapper(componentModel = "spring")
public interface TaskJournalMapper {

    TasksJournal mapToTaskJournal(TaskJournalDto taskJournalDto);

    @Mapping(source = "documentId", target = "taskJournal.documentId")
    TasksJournal mapDocumentIdWithTaskJournal(@MappingTarget TasksJournal taskJournal, Long documentId);

    ResponseTaskJournalDto mapToFullTasksJournalDto(TasksJournal tasksJournal);
}