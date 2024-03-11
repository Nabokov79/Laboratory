package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.defects.DefectDto;
import ru.nabokovsg.result.dto.defects.FullDefectDto;

import java.util.List;

public interface DefectsService {

    FullDefectDto save(DefectDto defectDto);

    FullDefectDto update(DefectDto defectDto);

    List<FullDefectDto> getAll(Long id);

    void delete(Long id);
}