package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.result.dto.сontrolPoint.ControlPointMeasurementDto;
import ru.nabokovsg.result.models.ControlPointMeasurement;
import ru.nabokovsg.result.models.builders.MeasurementBuilder;

@Mapper(componentModel = "spring")
public interface ControlPointMeasurementMapper {

    ControlPointMeasurement mapToControlPointMeasurement(MeasurementBuilder builder);

    ControlPointMeasurementDto mapToControlPointMeasurementDto(ControlPointMeasurement measurement);
}