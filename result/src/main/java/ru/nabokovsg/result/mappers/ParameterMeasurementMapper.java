package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.result.models.ParameterMeasurement;
import ru.nabokovsg.result.models.Parameters;

@Mapper(componentModel = "spring")
public interface ParameterMeasurementMapper {

    ParameterMeasurement mapToParameterMeasurement(Parameters parameter);
}