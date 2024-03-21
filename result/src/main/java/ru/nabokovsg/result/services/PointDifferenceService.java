package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.ControlPointMeasurement;

import java.util.List;

public interface PointDifferenceService {

    void save(ControlPointMeasurement controlPointMeasurement, List<ControlPoint> controlPoints);

    void update(ControlPointMeasurement controlPointMeasurement, List<ControlPoint> controlPoints);
}