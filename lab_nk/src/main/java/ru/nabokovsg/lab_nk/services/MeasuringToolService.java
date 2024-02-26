package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.measuringTool.FullMeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.RequestParameters;

import java.util.List;

public interface MeasuringToolService {

    FullMeasuringToolDto save(MeasuringToolDto measuringToolDto);

    FullMeasuringToolDto update(MeasuringToolDto measuringToolDto);

    List<FullMeasuringToolDto> getAll(RequestParameters parameters);

    void delete(Long id);
}