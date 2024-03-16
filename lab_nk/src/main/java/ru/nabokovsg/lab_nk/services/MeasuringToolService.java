package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.measuringTool.ResponseMeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.SearchParameters;

import java.util.List;

public interface MeasuringToolService {

    ResponseMeasuringToolDto save(MeasuringToolDto measuringToolDto);

    ResponseMeasuringToolDto update(MeasuringToolDto measuringToolDto);

    List<ResponseMeasuringToolDto> getAll(SearchParameters parameters);

    void delete(Long id);
}