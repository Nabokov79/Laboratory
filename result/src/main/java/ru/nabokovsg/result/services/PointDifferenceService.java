package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.ControlPoint;

import java.util.List;

public interface PointDifferenceService {

    void save(List<ControlPoint> controlPoints);

    void update(List<ControlPoint> controlPoints);
}