package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.result.dto.defects.DefectMeasurementDto;
import ru.nabokovsg.result.dto.defects.ResponseDefectMeasurementDto;
import ru.nabokovsg.result.models.Defect;
import ru.nabokovsg.result.models.DefectMeasurement;
import ru.nabokovsg.result.models.VisualMeasuringSurvey;

@Mapper(componentModel = "spring")
public interface DefectMeasurementMapper {

    @Mapping(source = "defectMeasurementDto.elementId", target = "elementId")
    @Mapping(source = "defectMeasurementDto.partElementId", target = "partElementId")
    @Mapping(source = "defect.id", target = "defectId")
    @Mapping(source = "defect.defectName", target = "defectName")
    @Mapping(source = "defect.notMeetRequirements", target = "notMeetRequirements")
    @Mapping(source = "defect.useToCalculateResidualThickness", target = "useToCalculateResidualThickness")
    @Mapping(source = "visualMeasuringSurvey", target = "visualMeasuringSurvey")
    @Mapping(source = "defectMeasurementDto.id", target = "id")
    DefectMeasurement mapToDefectMeasurement(DefectMeasurementDto defectMeasurementDto
                                           , Defect defect
                                           , VisualMeasuringSurvey visualMeasuringSurvey);

    ResponseDefectMeasurementDto mapToResponseDefectMeasurementDto(DefectMeasurement defectMeasurement);
}