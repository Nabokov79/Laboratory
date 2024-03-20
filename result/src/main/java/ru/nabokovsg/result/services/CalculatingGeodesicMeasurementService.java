package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.Set;

public interface CalculatingGeodesicMeasurementService {

    void save(EquipmentDiagnosed equipmentDiagnosed, Set<GeodesicMeasurement> measurements);
    void update(EquipmentDiagnosed equipmentDiagnosed, Set<GeodesicMeasurement> measurements);
}
