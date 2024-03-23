package ru.nabokovsg.result.services.geodesic;

import ru.nabokovsg.result.dto.сontrolPoint.ControlPointMeasurementDto;
import ru.nabokovsg.result.models.builders.MeasurementBuilder;

public interface ControlPointMeasurementService {

    void save(MeasurementBuilder builder);

    void update(MeasurementBuilder builder);

    ControlPointMeasurementDto get(Long id);
}