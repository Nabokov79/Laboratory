package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.template.client.dto.HeaderDocumentDto;
import ru.nabokovsg.template.dto.protocolReport.FullProtocolReportTemplateDto;
import ru.nabokovsg.template.dto.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.template.dto.protocolReport.ShortProtocolReportTemplateDto;
import ru.nabokovsg.template.models.ProtocolReportTemplate;

@Mapper(componentModel = "spring")
public interface ProtocolReportTemplateMapper {

    @Mapping(source = "headerDocumentDto.id", target = "headerDocumentId")
    @Mapping(source = "headerDocumentDto.title", target = "title")
    @Mapping(source = "headerDocumentDto.heading", target = "heading")
    @Mapping(source = "protocolDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "protocolDto.userTextAfterHeading", target = "userTextAfterHeading")
    @Mapping(source = "protocolDto.id", target = "id")
    ProtocolReportTemplate mapToProtocolReportTemplate(ProtocolReportTemplateDto protocolDto
                                                     , HeaderDocumentDto headerDocumentDto);

    FullProtocolReportTemplateDto mapToFullProtocolReportTemplateDto(ProtocolReportTemplate protocol);

    ShortProtocolReportTemplateDto mapToShortProtocolReportTemplateDto(ProtocolReportTemplate protocol);
}