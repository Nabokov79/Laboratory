package ru.nabokovsg.document.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.lab_nk.dto.document.FullDocumentDto;
import ru.nabokovsg.lab_nk.models.Document;
import ru.nabokovsg.lab_nk.models.TasksJournal;
import ru.nabokovsg.lab_nk.models.enums.Status;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mapping(source = "taskJournal", target = " taskJournal")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "documentStatus", target = "documentStatus")
    @Mapping(source = "drawingStatus", target = "drawingStatus")
    @Mapping(source = "documentNumber", target = "documentNumber")
    @Mapping(source = "document.id", target = "id")
    Document mapToDocument(Document document, TasksJournal taskJournal
                         , LocalDate date, Status documentStatus
                         , Status drawingStatus, Integer documentNumber);

    FullDocumentDto mapToFullDocumentDto(Document document);
}