package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.defects.DefectMeasurementDto;
import ru.nabokovsg.result.dto.defects.ResponseDefectMeasurementDto;
import java.util.List;

public interface DefectMeasurementService {

    ResponseDefectMeasurementDto save(DefectMeasurementDto defectMeasurementDto);

    ResponseDefectMeasurementDto update(DefectMeasurementDto defectMeasurementDto);

   List<ResponseDefectMeasurementDto> getAll(Long id);

   void delete(Long id);
}