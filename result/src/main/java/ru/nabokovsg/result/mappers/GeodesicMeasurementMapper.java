package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import ru.nabokovsg.result.dto.geodesic.GeodesicMeasurementDto;
import ru.nabokovsg.result.dto.geodesic.ResponseGeodesicMeasurementDto;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.GeodesicMeasurement;

@Mapper(componentModel = "spring")
public interface GeodesicMeasurementMapper {

    @Mapping(source = "equipmentDiagnosed", target = "equipmentDiagnosed")
    @Mapping(source = "geodesicMeasurementDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "geodesicMeasurementDto.numberMeasurementLocation", target = "numberMeasurementLocation")
    @Mapping(source = "geodesicMeasurementDto.referencePointValue", target = "referencePointValue")
    @Mapping(source = "geodesicMeasurementDto.controlPointValue", target = "controlPointValue")
    @Mapping(source = "geodesicMeasurementDto.transitionValue", target = "transitionValue")
    @Mapping(source = "geodesicMeasurementDto.id", target = "id")
    GeodesicMeasurement mapToGeodesicMeasurement(GeodesicMeasurementDto geodesicMeasurementDto
                                               , EquipmentDiagnosed equipmentDiagnosed);

    ResponseGeodesicMeasurementDto mapToResponseGeodesicMeasurementDto(GeodesicMeasurement geodesicMeasurement);
}