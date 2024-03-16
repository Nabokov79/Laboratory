package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.template.dto.conclusion.ResponseConclusionTemplateDto;

public interface ConclusionTemplateService {

    ResponseConclusionTemplateDto save(ConclusionTemplateDto conclusionDto);

    ResponseConclusionTemplateDto update(ConclusionTemplateDto conclusionDto);

    void delete(Long id);
}