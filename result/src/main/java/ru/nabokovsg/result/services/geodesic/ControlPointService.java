package ru.nabokovsg.result.services.geodesic;

import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.ControlPointMeasurement;
import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.List;
import java.util.Set;

public interface ControlPointService {

    Set<ControlPoint> save(ControlPointMeasurement controlPointMeasurement, List<GeodesicMeasurement> measurements);

    Set<ControlPoint> update(ControlPointMeasurement controlPointMeasurement, List<GeodesicMeasurement> measurements);
}