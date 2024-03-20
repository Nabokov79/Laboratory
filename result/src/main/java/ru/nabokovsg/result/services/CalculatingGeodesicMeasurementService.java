package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.List;

public interface CalculatingGeodesicMeasurementService {

    List<GeodesicMeasurement> recalculateMeasurements(List<GeodesicMeasurement> geodesicMeasurements);
}
