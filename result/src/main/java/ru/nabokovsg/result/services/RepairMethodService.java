package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.repairMethod.FullRepairMethodDto;
import ru.nabokovsg.result.dto.repairMethod.RepairMethodDto;

import java.util.List;

public interface RepairMethodService {

    FullRepairMethodDto save(RepairMethodDto methodDto);

    FullRepairMethodDto update(RepairMethodDto methodDto);

   List<FullRepairMethodDto> getAll(Long id);

    void delete(Long id);
}