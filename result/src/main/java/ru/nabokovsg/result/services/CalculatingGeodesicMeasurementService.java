package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.List;

public interface CalculatingGeodesicMeasurementService {

    void calculate(EquipmentDiagnosed equipmentDiagnosed, List<GeodesicMeasurement> geodesicMeasurements);
}
