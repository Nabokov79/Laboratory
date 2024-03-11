package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.template.dto.conclusion.FullConclusionTemplateDto;

public interface ConclusionTemplateService {

    FullConclusionTemplateDto save(ConclusionTemplateDto conclusionDto);

    FullConclusionTemplateDto update(ConclusionTemplateDto conclusionDto);

    void delete(Long id);
}