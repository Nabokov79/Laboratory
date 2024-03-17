package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.List;

public interface ReferencePointService {

    void save(List<GeodesicMeasurement> measurements);
}