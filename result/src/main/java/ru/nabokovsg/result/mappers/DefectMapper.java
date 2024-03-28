package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.result.dto.defects.DefectDto;
import ru.nabokovsg.result.dto.defects.ResponseDefectDto;
import ru.nabokovsg.result.models.Defect;
import ru.nabokovsg.result.models.Parameters;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DefectMapper {

    @Mapping(source ="defectDto.equipmentTypeId", target = "equipmentTypeId")
    @Mapping(source ="defectDto.defectName", target = "defectName")
    @Mapping(source ="defectDto.notMeetRequirements", target = "notMeetRequirements")
    @Mapping(source ="defectDto.useToCalculateResidualThickness", target = "useToCalculateResidualThickness")
    @Mapping(source ="parameters", target = "parameters")
    @Mapping(source ="defectDto.id", target = "id")
    Defect mapToDefect(DefectDto defectDto, List<Parameters> parameters);

    ResponseDefectDto mapToFullDefectDto(Defect defect);
}