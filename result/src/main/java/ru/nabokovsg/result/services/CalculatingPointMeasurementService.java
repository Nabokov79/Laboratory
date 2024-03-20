package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.DeviationYear;
import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.List;

public interface CalculatingPointMeasurementService {

    Integer getMinMeasurement(List<GeodesicMeasurement> measurements);

    Integer getDeviation(Integer min, Integer controlPointValue);

    Integer getPrecipitation(Integer newDeviation, int year, List<DeviationYear> deviationYeas);
}