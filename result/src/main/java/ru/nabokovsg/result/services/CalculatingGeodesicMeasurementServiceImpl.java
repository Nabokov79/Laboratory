package ru.nabokovsg.result.services;

import org.springframework.stereotype.Component;
import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CalculatingGeodesicMeasurementServiceImpl implements CalculatingGeodesicMeasurementService {

    @Override
    public List<GeodesicMeasurement> recalculateMeasurements(List<GeodesicMeasurement> geodesicMeasurements) {
        int delta = 0;
        Map<Integer, GeodesicMeasurement> measurements = geodesicMeasurements.stream()
                                           .collect(Collectors.toMap(GeodesicMeasurement::getSequentialNumber, g -> g));
        for (int i = 1;  i <= measurements.size(); i++) {
            GeodesicMeasurement measurement = measurements.get(i);
            if (delta != 0) {
                if (measurement.getReferencePointValue() != null) {
                    measurement.setReferencePointValue(getNewMeasurementValue(measurement.getReferencePointValue(), delta));
                }
                measurement.setControlPointValue(getNewMeasurementValue(measurement.getControlPointValue(), delta));
                measurements.put(measurement.getSequentialNumber(), measurement);

            }
            delta = getDelta(measurement, delta);
        }
        return new ArrayList<>(measurements.values());
    }

    private Integer getDelta(GeodesicMeasurement measurement, Integer delta) {
        Integer measurementValue;
        if (measurement.getReferencePointValue() == null) {
            measurementValue = measurement.getControlPointValue();
        } else {
            measurementValue = measurement.getReferencePointValue();
        }
        if (measurement.getTransitionValue() != null) {
            if (delta > 0) {
                return measurementValue - measurement.getTransitionValue() + delta;
            }
            return measurementValue - measurement.getTransitionValue();
        }
        return delta;
    }


        private Integer getNewMeasurementValue(int measurementValue, int delta) {
        return measurementValue + delta;
    }
}