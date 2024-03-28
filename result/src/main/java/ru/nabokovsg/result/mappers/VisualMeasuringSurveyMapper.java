package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.VisualMeasuringSurvey;

@Mapper(componentModel = "spring")
public interface VisualMeasuringSurveyMapper {

    @Mapping(source = "equipmentDiagnosed", target = "equipmentDiagnosed")
    @Mapping(target = "id", ignore = true)
    VisualMeasuringSurvey mapToVisualMeasuringSurvey(EquipmentDiagnosed equipmentDiagnosed);
}