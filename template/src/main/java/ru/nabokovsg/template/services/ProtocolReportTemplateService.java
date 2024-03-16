package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.protocolReport.ResponseProtocolReportTemplateDto;
import ru.nabokovsg.template.dto.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.template.dto.protocolReport.ShortResponseProtocolReportTemplateDto;
import ru.nabokovsg.template.models.ConclusionTemplate;
import ru.nabokovsg.template.models.SubsectionTemplate;
import ru.nabokovsg.template.models.TableTemplate;

import java.util.List;

public interface ProtocolReportTemplateService {

    ShortResponseProtocolReportTemplateDto save(ProtocolReportTemplateDto protocolDto);

    ShortResponseProtocolReportTemplateDto update(ProtocolReportTemplateDto protocolDto);

    ResponseProtocolReportTemplateDto get(Long id);

    List<ShortResponseProtocolReportTemplateDto> getAll(Long id);

    void delete(Long id);

    void addTable(Long id, TableTemplate table);

    void addSubsection(Long id, SubsectionTemplate subsection);

     void addConclusion(Long headerDocumentId, ConclusionTemplate conclusion);
}