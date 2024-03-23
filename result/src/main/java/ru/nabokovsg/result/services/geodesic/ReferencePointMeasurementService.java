package ru.nabokovsg.result.services.geodesic;

import ru.nabokovsg.result.dto.referencePoint.ReferencePointDto;
import ru.nabokovsg.result.models.builders.MeasurementBuilder;

import java.util.List;

public interface ReferencePointMeasurementService {

    void save(MeasurementBuilder builder);

    void update(MeasurementBuilder builder);

    List<ReferencePointDto> getAll(Long id);
}