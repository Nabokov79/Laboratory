package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.DeviationYear;

import java.util.List;

public interface CalculatingPointMeasurementService {

    Integer getMinMeasurement(List<Integer>  calculatedHeights);

    Integer getDeviation(Integer min, Integer calculatedHeight);

    Integer getPrecipitation(Integer newDeviation, int year, List<DeviationYear> deviationYeas);
}