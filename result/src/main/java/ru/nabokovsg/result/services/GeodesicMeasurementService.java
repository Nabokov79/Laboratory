package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.geodesic.GeodesicMeasurementDto;
import ru.nabokovsg.result.dto.geodesic.GeodeticMeasurementEquipmentDto;
import ru.nabokovsg.result.dto.geodesic.ResponseGeodesicMeasurementDto;

import java.util.List;

public interface GeodesicMeasurementService {

    List<ResponseGeodesicMeasurementDto> save(GeodeticMeasurementEquipmentDto measurementsDto);

    ResponseGeodesicMeasurementDto update(GeodesicMeasurementDto measurementDto);

    List<ResponseGeodesicMeasurementDto> getAll(Long id);

    void delete(Long id);
}