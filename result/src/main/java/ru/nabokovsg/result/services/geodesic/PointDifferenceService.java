package ru.nabokovsg.result.services.geodesic;

import ru.nabokovsg.result.models.builders.MeasurementBuilder;

public interface PointDifferenceService {

    void save(MeasurementBuilder builder);

    void update(MeasurementBuilder builder);
}