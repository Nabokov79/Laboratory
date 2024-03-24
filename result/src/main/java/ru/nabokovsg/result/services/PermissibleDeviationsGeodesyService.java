package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.permissibleDeviations.ResponsePermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.dto.permissibleDeviations.PermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;

import java.util.List;

public interface PermissibleDeviationsGeodesyService {

    ResponsePermissibleDeviationsGeodesyDto save(PermissibleDeviationsGeodesyDto geodesyDto);

   ResponsePermissibleDeviationsGeodesyDto update(PermissibleDeviationsGeodesyDto geodesyDto);

    List<ResponsePermissibleDeviationsGeodesyDto> getAll(Long id);

    PermissibleDeviationsGeodesy getByParameters(EquipmentDiagnosed equipmentDiagnosed);

    void delete(Long id);
}