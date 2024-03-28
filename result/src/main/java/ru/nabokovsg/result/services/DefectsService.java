package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.defects.DefectDto;
import ru.nabokovsg.result.dto.defects.ResponseDefectDto;
import ru.nabokovsg.result.models.Defect;

import java.util.List;

public interface DefectsService {

    ResponseDefectDto save(DefectDto defectDto);

    ResponseDefectDto update(DefectDto defectDto);

    List<ResponseDefectDto> getAll(Long id);

    Defect get(Long id);

    void delete(Long id);
}