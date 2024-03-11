package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.protocolReport.FullProtocolReportTemplateDto;
import ru.nabokovsg.template.dto.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.template.dto.protocolReport.ShortProtocolReportTemplateDto;
import ru.nabokovsg.template.models.ConclusionTemplate;
import ru.nabokovsg.template.models.SubsectionTemplate;
import ru.nabokovsg.template.models.TableTemplate;

import java.util.List;

public interface ProtocolReportTemplateService {

    ShortProtocolReportTemplateDto save(ProtocolReportTemplateDto protocolDto);

    ShortProtocolReportTemplateDto update(ProtocolReportTemplateDto protocolDto);

    FullProtocolReportTemplateDto get(Long id);

    List<ShortProtocolReportTemplateDto> getAll(Long id);

    void delete(Long id);

    void addTable(Long id, TableTemplate table);

    void addSubsection(Long id, SubsectionTemplate subsection);

     void addConclusion(Long headerDocumentId, ConclusionTemplate conclusion);
}