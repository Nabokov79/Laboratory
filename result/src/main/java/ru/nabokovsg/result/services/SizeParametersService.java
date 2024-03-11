package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.sizeParameters.SizeParametersDto;
import ru.nabokovsg.result.models.SizeParameters;
import java.util.List;

public interface SizeParametersService {

    List<SizeParameters> save(List<SizeParametersDto> parametersDto);

    List<SizeParameters> update(List<SizeParametersDto> parametersDto);
}