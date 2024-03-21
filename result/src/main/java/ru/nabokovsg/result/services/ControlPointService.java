package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.ControlPointMeasurement;
import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.List;

public interface ControlPointService {

    List<ControlPoint> save(ControlPointMeasurement controlPointMeasurement, List<GeodesicMeasurement> measurements);

    List<ControlPoint> update(ControlPointMeasurement controlPointMeasurement, List<GeodesicMeasurement> measurements);
}