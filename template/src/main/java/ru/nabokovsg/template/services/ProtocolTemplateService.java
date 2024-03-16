package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.protocol.ResponseProtocolTemplateDto;
import ru.nabokovsg.template.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.template.dto.protocol.ShortResponseProtocolTemplateDto;
import ru.nabokovsg.template.models.AppendicesTemplate;
import ru.nabokovsg.template.models.ConclusionTemplate;
import ru.nabokovsg.template.models.SubsectionTemplate;
import ru.nabokovsg.template.models.TableTemplate;

import java.util.List;

public interface ProtocolTemplateService {

    ShortResponseProtocolTemplateDto save(ProtocolTemplateDto protocolDto);

    ShortResponseProtocolTemplateDto update(ProtocolTemplateDto protocolDto);

    ResponseProtocolTemplateDto get(Long id);

    List<ShortResponseProtocolTemplateDto> getAll();

    void addTable(Long id, TableTemplate table);

    void addSubsection(Long id, SubsectionTemplate subsection);

    void addConclusion(Long headerDocumentId, ConclusionTemplate conclusion);

    void addAppendices(Long equipmentTypeId, AppendicesTemplate appendices);
}