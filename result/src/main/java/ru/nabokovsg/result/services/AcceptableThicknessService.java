package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.acceptable.AcceptableThicknessDto;
import ru.nabokovsg.result.dto.acceptable.FullAcceptableThicknessDto;

import java.util.List;

public interface AcceptableThicknessService {

    FullAcceptableThicknessDto save(AcceptableThicknessDto thicknessDto);

    FullAcceptableThicknessDto update(AcceptableThicknessDto thicknessDto);

    List<FullAcceptableThicknessDto> getAll(Long id);

    void delete(Long id);
}