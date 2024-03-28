package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.result.dto.defectRepair.DefectRepairDto;
import ru.nabokovsg.result.dto.defectRepair.ResponseDefectRepairDto;
import ru.nabokovsg.result.models.CompletedRepairs;
import ru.nabokovsg.result.models.DetectedRepairs;
import ru.nabokovsg.result.models.VisualMeasuringSurvey;

@Mapper(componentModel = "spring")
public interface DetectedRepairsMapper {

    @Mapping(source = "defectRepairDto.elementId", target = "elementId")
    @Mapping(source = "defectRepairDto.partElementId", target = "partElementId")
    @Mapping(source = "defectRepairDto.repairId", target = "repairId")
    @Mapping(source = "repairs.repairName", target = "repairName")
    @Mapping(source = "visualMeasuringSurvey", target = "visualMeasuringSurvey")
    @Mapping(source = "defectRepairDto.id", target = "id")
    DetectedRepairs mapToDetectedRepairs(DefectRepairDto defectRepairDto
                                       , CompletedRepairs repairs
                                       , VisualMeasuringSurvey visualMeasuringSurvey);

    ResponseDefectRepairDto mapToResponseDefectRepairDto(DetectedRepairs detectedRepairs);
}