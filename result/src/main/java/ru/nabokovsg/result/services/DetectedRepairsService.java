package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.defectRepair.DefectRepairDto;
import ru.nabokovsg.result.dto.defectRepair.ResponseDefectRepairDto;

import java.util.List;

public interface DetectedRepairsService {

    ResponseDefectRepairDto save(DefectRepairDto defectRepairDto);

   ResponseDefectRepairDto update(DefectRepairDto defectRepairDto);

    List<ResponseDefectRepairDto> getAll(Long id);

    void delete(Long id);
}