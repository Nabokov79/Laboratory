package ru.nabokovsg.template.services;

import ru.nabokovsg.template.models.MeasuringToolTemplate;

import java.util.List;

public interface MeasuringToolTemplateService {

    List<MeasuringToolTemplate> save(List<Long> ids);
}