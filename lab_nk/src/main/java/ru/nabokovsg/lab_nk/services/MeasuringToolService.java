package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.measuringTool.FullMeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.SearchParameters;

import java.util.List;

public interface MeasuringToolService {

    FullMeasuringToolDto save(MeasuringToolDto measuringToolDto);

    FullMeasuringToolDto update(MeasuringToolDto measuringToolDto);

    List<FullMeasuringToolDto> getAll(SearchParameters parameters);

    void delete(Long id);
}