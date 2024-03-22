package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.DeviationYear;
import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.List;

public interface CalculationGeodesyPointsService {

    List<GeodesicMeasurement> recalculateMeasurements(List<GeodesicMeasurement> geodesicMeasurements);

    Integer getMinMeasurement(List<Integer>  calculatedHeights);

    Integer getDeviation(Integer min, Integer calculatedHeight);

    Integer getPrecipitation(Integer newDeviation, int year, List<DeviationYear> deviationYeas);
}
