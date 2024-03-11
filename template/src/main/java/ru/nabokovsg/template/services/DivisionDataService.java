package ru.nabokovsg.template.services;

import ru.nabokovsg.template.dto.header.HeaderTemplateDto;
import ru.nabokovsg.template.models.DivisionData;

public interface DivisionDataService {

    DivisionData save(HeaderTemplateDto headerDto);

    DivisionData update(Long divisionId, HeaderTemplateDto headerDto);
}