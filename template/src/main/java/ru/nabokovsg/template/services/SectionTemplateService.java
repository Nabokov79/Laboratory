package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.section.SectionDataTemplateDto;
import ru.nabokovsg.template.dto.section.FullSectionTemplateDto;
import ru.nabokovsg.template.dto.section.SectionTemplateDto;
import ru.nabokovsg.template.dto.section.ShortSectionTemplateDto;
import ru.nabokovsg.template.models.ProtocolReportTemplate;
import ru.nabokovsg.template.models.SubsectionTemplate;

import java.util.List;

public interface SectionTemplateService {

    List<ShortSectionTemplateDto> save(SectionDataTemplateDto sectionsDto);

    List<ShortSectionTemplateDto> update(List<SectionTemplateDto> sectionsDto);

    FullSectionTemplateDto get(Long id);

    List<ShortSectionTemplateDto> getAll(Long id);

    void delete(Long id);

    void addProtocol(Long id, ProtocolReportTemplate protocol);

    void addSubsection(Long id, SubsectionTemplate subsection);
}