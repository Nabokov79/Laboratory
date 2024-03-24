package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.result.dto.hardnessMeasurement.HardnessMeasurementDto;
import ru.nabokovsg.result.dto.hardnessMeasurement.ResponseHardnessMeasurementDto;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.HardnessMeasurement;

@Mapper(componentModel = "spring")
public interface HardnessMeasurementMapper {

    @Mapping(source = "equipmentDiagnosed", target = "equipmentDiagnosed")
    @Mapping(source = "measurementDto.elementId", target = "elementId")
    @Mapping(source = "measurementDto.partElementId", target = "partElementId")
    @Mapping(source = "measurementDto.measurementNumber", target = "measurementNumber")
    @Mapping(source = "measurementDto.measurementValue", target = "measurementValue")
    @Mapping(source = "measurementDto.id", target = "id")
    HardnessMeasurement mapToHardnessMeasurement(HardnessMeasurementDto measurementDto, EquipmentDiagnosed equipmentDiagnosed);

    ResponseHardnessMeasurementDto mapToResponseHardnessMeasurementDto(HardnessMeasurement measurement);


    @Mapping(source = "acceptableValue", target = "acceptableValue")
    HardnessMeasurement mapHardnessMeasurementWithAcceptableValue(@MappingTarget HardnessMeasurement measurement, Boolean acceptableValue);
}