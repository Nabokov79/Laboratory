package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculatingGeodesicMeasurementServiceImpl implements CalculatingGeodesicMeasurementService {
    private final ReferencePointService referencePointService;
    private final ControlPointService controlPointService;

    @Override
    public void save(EquipmentDiagnosed equipmentDiagnosed, Set<GeodesicMeasurement> measurements) {
        List<GeodesicMeasurement> geodesicMeasurements = recalculateMeasurements(measurements);
        referencePointService.save(equipmentDiagnosed, geodesicMeasurements);
        controlPointService.save(geodesicMeasurements);
    }

    @Override
    public void update(EquipmentDiagnosed equipmentDiagnosed, Set<GeodesicMeasurement> measurements) {
        List<GeodesicMeasurement> geodesicMeasurements = recalculateMeasurements(measurements);
        referencePointService.update(equipmentDiagnosed, geodesicMeasurements);
        controlPointService.update(geodesicMeasurements);
    }

    private List<GeodesicMeasurement> recalculateMeasurements(Set<GeodesicMeasurement> geodesicMeasurements) {
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