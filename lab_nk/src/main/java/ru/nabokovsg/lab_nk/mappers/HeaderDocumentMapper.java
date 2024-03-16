package ru.nabokovsg.lab_nk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.lab_nk.dto.headerDocument.ResponseHeaderDocumentDto;
import ru.nabokovsg.lab_nk.dto.headerDocument.HeaderDocumentDto;
import ru.nabokovsg.lab_nk.models.HeaderDocument;
import ru.nabokovsg.lab_nk.models.enums.TypeDocument;

@Mapper(componentModel = "spring")
public interface HeaderDocumentMapper {

    @Mapping(source = "typeDocument", target = "typeDocument")
    @Mapping(source = "headerDocumentDto.id", target = "id")
    HeaderDocument mapToHeaderDocument(HeaderDocumentDto headerDocumentDto, TypeDocument typeDocument);

    ResponseHeaderDocumentDto mapToFullHeaderDocumentDto(HeaderDocument headerDocument);
}