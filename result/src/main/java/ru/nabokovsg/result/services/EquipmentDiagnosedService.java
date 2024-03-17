package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.equipmentDiagnosed.EquipmentDiagnosedDto;
import ru.nabokovsg.result.dto.geodesic.GeodeticMeasurementEquipmentDto;
import ru.nabokovsg.result.models.EquipmentDiagnosed;

public interface EquipmentDiagnosedService {

    void save(EquipmentDiagnosedDto equipmentDto);

    EquipmentDiagnosed addGeodeticMeasurementData(GeodeticMeasurementEquipmentDto equipmentDto);
}