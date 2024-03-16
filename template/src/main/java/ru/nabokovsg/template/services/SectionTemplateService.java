package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.section.SectionDataTemplateDto;
import ru.nabokovsg.template.dto.section.ResponseSectionTemplateDto;
import ru.nabokovsg.template.dto.section.SectionTemplateDto;
import ru.nabokovsg.template.dto.section.ShortResponseSectionTemplateDto;
import ru.nabokovsg.template.models.ProtocolReportTemplate;
import ru.nabokovsg.template.models.SubsectionTemplate;

import java.util.List;

public interface SectionTemplateService {

    List<ShortResponseSectionTemplateDto> save(SectionDataTemplateDto sectionsDto);

    List<ShortResponseSectionTemplateDto> update(List<SectionTemplateDto> sectionsDto);

    ResponseSectionTemplateDto get(Long id);

    List<ShortResponseSectionTemplateDto> getAll(Long id);

    void delete(Long id);

    void addProtocol(Long id, ProtocolReportTemplate protocol);

    void addSubsection(Long id, SubsectionTemplate subsection);
}