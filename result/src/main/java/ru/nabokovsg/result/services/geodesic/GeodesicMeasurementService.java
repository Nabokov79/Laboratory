package ru.nabokovsg.result.services.geodesic;

import ru.nabokovsg.result.dto.geodesic.GeodesicMeasurementDto;
import ru.nabokovsg.result.dto.geodesic.GeodeticMeasurementEquipmentDto;
import ru.nabokovsg.result.dto.geodesic.ResponseGeodesicMeasurementDto;

import java.util.List;

public interface GeodesicMeasurementService {

    List<ResponseGeodesicMeasurementDto> save(GeodeticMeasurementEquipmentDto measurementsDto);

    List<ResponseGeodesicMeasurementDto> update(List<GeodesicMeasurementDto> measurementsDto);

    List<ResponseGeodesicMeasurementDto> getAll(Long id);

    void delete(Long id);
}