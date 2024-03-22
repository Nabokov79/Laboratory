package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.result.models.ControlPointMeasurement;
import ru.nabokovsg.result.models.PointDifference;
import ru.nabokovsg.result.models.enums.GeodesicPointType;

@Mapper(componentModel = "spring")
public interface PointDifferenceMapper {

    @Mapping(source = "geodesicPointType", target = "geodesicPointType")
    @Mapping(source = "firstPlaceNumber", target = "firstPlaceNumber")
    @Mapping(source = "secondPlaceNumber", target = "secondPlaceNumber")
    @Mapping(source = "difference", target = "difference")
    @Mapping(target = "id", ignore = true)
    PointDifference mapToPointDifference(GeodesicPointType geodesicPointType
                                       , Integer firstPlaceNumber
                                       , Integer secondPlaceNumber
                                       , Integer difference);

    @Mapping(source = "controlPointMeasurement", target = "controlPointMeasurement")
    @Mapping(target = "id", ignore = true)
    PointDifference mapPointDifferenceWithControlPointMeasurement(@MappingTarget PointDifference pointDifference
                                                                    , ControlPointMeasurement controlPointMeasurement);
}