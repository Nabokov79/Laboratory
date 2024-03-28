package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.repairMethod.ResponseCompletedRepairsDto;
import ru.nabokovsg.result.dto.repairMethod.CompletedRepairsDto;
import ru.nabokovsg.result.models.CompletedRepairs;

import java.util.List;

public interface CompletedRepairsService {

    ResponseCompletedRepairsDto save(CompletedRepairsDto methodDto);

    ResponseCompletedRepairsDto update(CompletedRepairsDto methodDto);

   List<ResponseCompletedRepairsDto> getAll(Long id);

    void delete(Long id);

    CompletedRepairs getById(Long id);
}