package ru.nabokovsg.document.service;

import ru.nabokovsg.document.dto.remark.ResponseRemarkDto;
import ru.nabokovsg.document.dto.remark.RemarkDto;
import java.util.List;

public interface RemarkService {

    ResponseRemarkDto save(RemarkDto remarkDto);

    ResponseRemarkDto update(RemarkDto remarkDto);

    List<ResponseRemarkDto> getAll(Long id, Boolean inspector);
}