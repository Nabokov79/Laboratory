package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.equipmentDiagnosed.EquipmentDiagnosedDto;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.builders.SearchParametersBuilder;

public interface EquipmentDiagnosedService {

    void save(EquipmentDiagnosedDto equipmentDto);

    EquipmentDiagnosed add(Long taskJournalId, Long equipmentId, boolean full);

    EquipmentDiagnosed getEquipmentDiagnosedData(SearchParametersBuilder parameters);
}