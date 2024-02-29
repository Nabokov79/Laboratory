package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.remark.FullRemarkDto;
import ru.nabokovsg.lab_nk.dto.remark.RemarkDto;
import java.util.List;

public interface RemarkService {

    FullRemarkDto save(RemarkDto remarkDto);

    FullRemarkDto update(RemarkDto remarkDto);

    List<FullRemarkDto> getAll(Long id, Boolean inspector);
}