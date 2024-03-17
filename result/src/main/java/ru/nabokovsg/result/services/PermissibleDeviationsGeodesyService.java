package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.geodesy.FullPermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.dto.geodesy.PermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;

import java.util.List;

public interface PermissibleDeviationsGeodesyService {

    FullPermissibleDeviationsGeodesyDto save(PermissibleDeviationsGeodesyDto geodesyDto);

   FullPermissibleDeviationsGeodesyDto update(PermissibleDeviationsGeodesyDto geodesyDto);

    List<FullPermissibleDeviationsGeodesyDto> getAll(Long id);

    PermissibleDeviationsGeodesy getByParameters(EquipmentDiagnosed equipmentDiagnosed);

    void delete(Long id);
}