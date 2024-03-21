package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.result.dto.referencePoint.ReferencePointDto;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.ReferencePoint;

@Mapper(componentModel = "spring")
public interface ReferencePointMapper {

    @Mapping(source = "deviation", target = "point.deviation")
    @Mapping(source = "precipitation", target = "point.precipitation")
    ReferencePoint mapToReferencePointData(@MappingTarget ReferencePoint point
                                     , Integer deviation
                                     , Integer precipitation);

    @Mapping(source = "placeNumber", target = "placeNumber")
    @Mapping(source = "calculatedHeight", target = "calculatedHeight")
    @Mapping(source = "equipmentDiagnosed", target = "equipmentDiagnosed")
    @Mapping(target = "id", ignore = true)
    ReferencePoint mapToReferencePoint(EquipmentDiagnosed equipmentDiagnosed
                                     , Integer placeNumber
                                     , Integer calculatedHeight);

    @Mapping(source = "placeNumber", target = "placeNumber")
    @Mapping(source = "calculatedHeight", target = "calculatedHeight")
    @Mapping(source = "equipmentDiagnosed", target = "equipmentDiagnosed")
    @Mapping(source = "id", target = "id")
    ReferencePoint mapToUpdateReferencePoint(Long id, EquipmentDiagnosed equipmentDiagnosed
            , Integer placeNumber
            , Integer calculatedHeight);

    ReferencePointDto mapToReferencePointDto(ReferencePoint point);
}
