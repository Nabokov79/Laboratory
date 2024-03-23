package ru.nabokovsg.result.services.geodesic;

import org.springframework.stereotype.Service;
import ru.nabokovsg.result.models.DeviationYear;
import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalculationGeodesyPointsServiceImpl implements CalculationGeodesyPointsService {

    @Override
    public List<GeodesicMeasurement> recalculateMeasurements(List<GeodesicMeasurement> geodesicMeasurements) {
        int delta = 0;
        Map<Integer, GeodesicMeasurement> measurements = geodesicMeasurements.stream()
                                           .collect(Collectors.toMap(GeodesicMeasurement::getSequentialNumber, g -> g));
        for (int i = 1;  i <= measurements.size(); i++) {
            GeodesicMeasurement measurement = measurements.get(i);
            measurements.put(measurement.getSequentialNumber(), getRecalculateMeasurement(measurement, delta));
            if (measurement.getTransitionValue() != null) {
                delta = getDelta(measurement.getControlPointValue(), measurement.getTransitionValue());
            }
        }
        return new ArrayList<>(measurements.values());
    }

    public GeodesicMeasurement getRecalculateMeasurement(GeodesicMeasurement measurement, int delta) {
        if (measurement.getReferencePointValue() != null) {
            measurement.setReferencePointValue(
                    getSumMeasurementAndDelta(measurement.getReferencePointValue(), delta)
            );
        }
        measurement.setControlPointValue(getSumMeasurementAndDelta(measurement.getControlPointValue(), delta));
        return measurement;
    }

    @Override
    public Integer getMinMeasurement(List<Integer> calculatedHeights) {
        Optional<Integer> min = calculatedHeights.stream()
                                                 .filter(Objects::nonNull)
                                                 .min(Integer::compareTo);
        if (min.isEmpty()) {
            return 0;
        } else {
            return min.get();
        }
    }

    @Override
    public Integer getDeviation(Integer firstNumber, Integer secondNumber) {
        return firstNumber - secondNumber;
    }

    @Override
    public Integer getPrecipitation(Integer newDeviation, int year, List<DeviationYear> deviationYeas) {
        if (deviationYeas == null) {
            return 0;
        }
        return newDeviation - deviationYeas.stream()
                                            .collect(Collectors.toMap(DeviationYear::getYear
                                                    , DeviationYear::getDeviation))
                                            .get(year);
    }

    private Integer getDelta(int measurementValue, int transitionValue) {
        return measurementValue - transitionValue;
    }

        private Integer getSumMeasurementAndDelta(int measurementValue, int delta) {
        return measurementValue + delta;
    }
}