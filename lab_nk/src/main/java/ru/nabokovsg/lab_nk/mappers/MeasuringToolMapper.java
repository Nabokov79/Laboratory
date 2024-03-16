package ru.nabokovsg.lab_nk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.lab_nk.dto.measuringTool.ResponseMeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.SearchParameters;
import ru.nabokovsg.lab_nk.models.LaboratoryEmployee;
import ru.nabokovsg.lab_nk.models.MeasuringTool;

@Mapper(componentModel = "spring")
public interface MeasuringToolMapper {

    MeasuringTool mapToMeasuringTool(MeasuringToolDto measuringToolDto);

    @Mapping(source = "employee", target = "employee")
    @Mapping(source = "measuringTool.id", target = "id")
    MeasuringTool mapMeasuringToolWithLaboratoryEmployee(MeasuringTool measuringTool, LaboratoryEmployee employee);
    ResponseMeasuringToolDto mapToFullMeasuringToolDto(MeasuringTool newMeasuringTool);

    SearchParameters mapToRequestParameters(MeasuringToolDto measuringToolDto);
}