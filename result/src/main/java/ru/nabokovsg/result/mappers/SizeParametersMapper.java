package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.result.dto.sizeParameters.SizeParametersDto;
import ru.nabokovsg.result.models.SizeParameters;

@Mapper(componentModel = "spring")
public interface SizeParametersMapper {

    SizeParameters mapToSizeParameters(SizeParametersDto parameterDto);
}