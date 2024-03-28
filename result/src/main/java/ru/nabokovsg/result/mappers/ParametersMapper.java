package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.result.dto.parameters.ParametersDto;
import ru.nabokovsg.result.models.Parameters;
import ru.nabokovsg.result.models.enums.ActionsOnParameter;

@Mapper(componentModel = "spring")
public interface ParametersMapper {

    @Mapping(source = "parameterDto.parameterName", target = "parameterName")
    @Mapping(source = "parameterDto.unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "parameterDto.useCalculateThickness", target = "useCalculateThickness")
    @Mapping(source = "actionsOnParameter", target = "actionsOnParameter")
    @Mapping(source = "parameterDto.id", target = "id")
    Parameters mapToParameters(ParametersDto parameterDto, ActionsOnParameter actionsOnParameter);
}