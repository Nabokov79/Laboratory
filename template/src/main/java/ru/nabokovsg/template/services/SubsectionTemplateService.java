package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.subsection.ResponseSubsectionTemplateDto;
import ru.nabokovsg.template.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.template.models.TableTemplate;

public interface SubsectionTemplateService {

    ResponseSubsectionTemplateDto save(SubsectionTemplateDto subsectionDto);

    ResponseSubsectionTemplateDto update(SubsectionTemplateDto subsectionsDto);

    ResponseSubsectionTemplateDto get(Long id);

    void delete(Long id);

    void addTable(Long id, TableTemplate table);
}