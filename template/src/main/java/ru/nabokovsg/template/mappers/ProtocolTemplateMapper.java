package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.template.client.dto.HeaderDocumentDto;
import ru.nabokovsg.template.dto.protocol.FullProtocolTemplateDto;
import ru.nabokovsg.template.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.template.dto.protocol.ShortProtocolTemplateDto;
import ru.nabokovsg.template.models.HeaderTemplate;
import ru.nabokovsg.template.models.ProtocolTemplate;

@Mapper(componentModel = "spring")
public interface ProtocolTemplateMapper {

    @Mapping(source = "header", target = "leftHeaderTemplate")
    @Mapping(source = "protocolDto.equipmentTypeId", target = "equipmentTypeId")
    @Mapping(source = "headerDocumentDto.id", target = "headerDocumentId")
    @Mapping(source = "headerDocumentDto.title", target = "title")
    @Mapping(source = "headerDocumentDto.heading", target = "heading")
    @Mapping(source = "protocolDto.id", target = "id")
    ProtocolTemplate mapToProtocolTemplate(ProtocolTemplateDto protocolDto
                                         , HeaderDocumentDto headerDocumentDto
                                         , HeaderTemplate header);

    ShortProtocolTemplateDto mapToShortProtocolTemplateDto(ProtocolTemplate protocolTemplate);

    FullProtocolTemplateDto mapToFullProtocolTemplateDto(ProtocolTemplate protocolTemplate);
}