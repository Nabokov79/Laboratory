package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.ControlPointMeasurement;

@Mapper(componentModel = "spring")
public interface ControlPointMapper {

    @Mapping(source = "placeNumber", target = "placeNumber")
    @Mapping(source = "calculatedHeight", target = "calculatedHeight")
    @Mapping(source = "deviation", target = "deviation")
    @Mapping(source = "controlPointMeasurement", target = "controlPointMeasurement")
    @Mapping(source = "id", target = "id")
    ControlPoint mapToControlPoint(Long id
                                 , Integer placeNumber
                                 , Integer calculatedHeight
                                 , Integer deviation
                                 , ControlPointMeasurement controlPointMeasurement);
}