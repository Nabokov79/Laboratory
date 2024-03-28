package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.parameters.ParametersDto;
import ru.nabokovsg.result.models.Parameters;
import java.util.List;

public interface ParametersService {

    List<Parameters> save(List<ParametersDto> parametersDto);

    List<Parameters> update(List<ParametersDto> parametersDto);
}