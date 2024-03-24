package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.hardnessMeasurement.HardnessMeasurementDataDto;
import ru.nabokovsg.result.dto.hardnessMeasurement.HardnessMeasurementDto;
import ru.nabokovsg.result.dto.hardnessMeasurement.ResponseHardnessMeasurementDto;

import java.util.List;

public interface HardnessMeasurementService {

    List<ResponseHardnessMeasurementDto> save(HardnessMeasurementDataDto measurementsDto);

    List<ResponseHardnessMeasurementDto> update(List<HardnessMeasurementDto> measurementsDto);

    List<ResponseHardnessMeasurementDto> getAll(Long id);
}