package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.GeodesicMeasurement;
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;

import java.util.List;

public interface ReferencePointService {

    void save(EquipmentDiagnosed equipmentDiagnosed
            , List<GeodesicMeasurement> measurements
            , PermissibleDeviationsGeodesy permissibleDeviations);
    void update(EquipmentDiagnosed equipmentDiagnosed
              , List<GeodesicMeasurement> measurements
              , PermissibleDeviationsGeodesy permissibleDeviations);
}