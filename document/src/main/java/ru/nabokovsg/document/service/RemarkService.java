package ru.nabokovsg.document.service;

import ru.nabokovsg.document.dto.remark.FullRemarkDto;
import ru.nabokovsg.document.dto.remark.RemarkDto;
import java.util.List;

public interface RemarkService {

    FullRemarkDto save(RemarkDto remarkDto);

    FullRemarkDto update(RemarkDto remarkDto);

    List<FullRemarkDto> getAll(Long id, Boolean inspector);
}