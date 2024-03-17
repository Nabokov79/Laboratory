package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.GeodesicMeasurement;
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;
import ru.nabokovsg.result.repository.CalculatingGeodesicMeasurementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculatingGeodesicMeasurementServiceImpl implements CalculatingGeodesicMeasurementService {

    private final CalculatingGeodesicMeasurementRepository repository;
    private final PermissibleDeviationsGeodesyService geodesyService;
    private final ReferencePointService referencePointService;
    private final ControlPointService controlPointService;

    @Override
    public void calculate(EquipmentDiagnosed equipmentDiagnosed, List<GeodesicMeasurement> geodesicMeasurements) {
        PermissibleDeviationsGeodesy permissibleDeviationsGeodesy = geodesyService.getByParameters(equipmentDiagnosed);
        List<GeodesicMeasurement> measurements = recalculate(geodesicMeasurements);
        referencePointService.save(measurements);
        controlPointService.save(measurements);
    }

    private List<GeodesicMeasurement> recalculate(List<GeodesicMeasurement> geodesicMeasurements) {
        Integer transitionValue = 0;
        Map<Integer, GeodesicMeasurement> measurements = geodesicMeasurements.stream()
                                           .collect(Collectors.toMap(GeodesicMeasurement::getSequentialNumber, g -> g));
        for (int i = 1;  i == measurements.size() + 1; i++) {
            GeodesicMeasurement measurement = measurements.get(i);
            if (transitionValue != 0) {
                measurement.setReferencePointValue(sum(transitionValue, measurement.getReferencePointValue()));
                measurement.setControlPointValue(sum(transitionValue,measurement.getControlPointValue()));
                measurements.put(measurement.getSequentialNumber(), measurement);
            }
            if (measurement.getTransitionValue() != null) {
                transitionValue = sum(transitionValue, measurement.getTransitionValue());
            }

        }
        return new ArrayList<>(measurements.values());
    }

    private void calculatingReferencePoint(List<GeodesicMeasurement> geodesicMeasurements) {

    }

    private void calculatingControlPoint(List<GeodesicMeasurement> geodesicMeasurements) {

    }

    private Integer sum(Integer transitionValue, Integer measuredValue) {
        return transitionValue + measuredValue;
    }
}