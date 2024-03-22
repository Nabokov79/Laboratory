package ru.nabokovsg.result.services;

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
            if (delta != 0) {
                if (measurement.getReferencePointValue() != null) {
                    measurement.setReferencePointValue(
                            getNewMeasurementValue(measurement.getReferencePointValue(), delta)
                    );
                }
                measurement.setControlPointValue(getNewMeasurementValue(measurement.getControlPointValue(), delta));
                measurements.put(measurement.getSequentialNumber(), measurement);

            }
            delta = getDelta(measurement, delta);
        }
        return new ArrayList<>(measurements.values());
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
    public Integer getDeviation(Integer min, Integer calculatedHeight) {
        return min - calculatedHeight;
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

    private Integer getDelta(GeodesicMeasurement measurement, Integer delta) {
        if (measurement.getTransitionValue() != null) {
            return measurement.getControlPointValue() - measurement.getTransitionValue() + delta;
        }
        return delta;
    }

        private Integer getNewMeasurementValue(int measurementValue, int delta) {
        return measurementValue + delta;
    }
}