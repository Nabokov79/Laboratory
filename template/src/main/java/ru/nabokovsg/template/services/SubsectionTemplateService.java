package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.subsection.FullSubsectionTemplateDto;
import ru.nabokovsg.template.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.template.models.TableTemplate;
import ru.nabokovsg.template.models.enums.TemplateType;

public interface SubsectionTemplateService {

    FullSubsectionTemplateDto save(SubsectionTemplateDto subsectionDto);

    FullSubsectionTemplateDto update(SubsectionTemplateDto subsectionsDto);

    FullSubsectionTemplateDto get(Long id);

    void delete(Long id);

    void addTable(Long id, TableTemplate table);
}