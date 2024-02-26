package ru.nabokovsg.lab_nk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.lab_nk.dto.measuringTool.FullMeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.RequestParameters;
import ru.nabokovsg.lab_nk.models.LaboratoryEmployee;
import ru.nabokovsg.lab_nk.models.MeasuringTool;

@Mapper(componentModel = "spring")
public interface MeasuringToolMapper {

    MeasuringTool mapToMeasuringTool(MeasuringToolDto measuringToolDto);

    @Mapping(source = "employee", target = "employee")
    @Mapping(source = "measuringTool.id", target = "id")
    MeasuringTool mapMeasuringToolWithLaboratoryEmployee(MeasuringTool measuringTool, LaboratoryEmployee employee);
    FullMeasuringToolDto mapToFullMeasuringToolDto(MeasuringTool newMeasuringTool);

    MeasuringToolDto mapToMeasuringToolDto(RequestParameters parameters);
}