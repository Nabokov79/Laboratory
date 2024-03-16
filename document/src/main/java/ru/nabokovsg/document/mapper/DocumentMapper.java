package ru.nabokovsg.document.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.document.dto.document.TaskJournalDto;
import ru.nabokovsg.document.dto.document.ResponseDocumentDto;
import ru.nabokovsg.document.models.Document;
import ru.nabokovsg.document.models.Subscriber;
import ru.nabokovsg.document.models.enums.DocumentStatus;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mapping(source = "taskJournalDto.id", target = "taskJournalId")
    @Mapping(source = "taskJournalDto.headerDocument.title", target = "title")
    @Mapping(source = "taskJournalDto.headerDocument.heading", target = "heading")
    @Mapping(source = "taskJournalDto.address", target = "address")
    @Mapping(source = "taskJournalDto.equipment", target = "equipment")
    @Mapping(source = "taskJournalDto.date", target = "date")
    @Mapping(source = "chief", target = "chief")
    @Mapping(source = "documentStatus", target = "documentStatus")
    @Mapping(source = "drawingStatus", target = "drawingStatus")
    @Mapping(source = "documentNumber", target = "documentNumber")
    @Mapping(target = "id", ignore = true)
    Document mapToDocument(TaskJournalDto taskJournalDto
                         , Subscriber chief
                         , Integer documentNumber
                         , DocumentStatus documentStatus
                         , DocumentStatus drawingStatus);

    ResponseDocumentDto mapToFullDocumentDto(Document document);
}