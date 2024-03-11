package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.geodesy.FullPermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.dto.geodesy.PermissibleDeviationsGeodesyDto;

import java.util.List;

public interface PermissibleDeviationsGeodesyService {

    FullPermissibleDeviationsGeodesyDto save(PermissibleDeviationsGeodesyDto geodesyDto);

   FullPermissibleDeviationsGeodesyDto update(PermissibleDeviationsGeodesyDto geodesyDto);

    List<FullPermissibleDeviationsGeodesyDto> getAll(Long id);

    void delete(Long id);
}