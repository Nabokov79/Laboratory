package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.List;

public interface ControlPointService {

    void save(List<GeodesicMeasurement> measurements);

    void update(List<GeodesicMeasurement> measurements);
}