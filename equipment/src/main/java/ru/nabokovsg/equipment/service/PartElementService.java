package ru.nabokovsg.equipment.service;

import ru.nabokovsg.equipment.dto.partElement.FullPartElementDto;
import ru.nabokovsg.equipment.dto.partElement.PartElementDto;
import ru.nabokovsg.equipment.dto.partElement.ShortPartElementDto;

import java.util.List;

public interface PartElementService {

    FullPartElementDto save(PartElementDto partElementDto);

    FullPartElementDto update(PartElementDto partElementDto);

    FullPartElementDto get(Long id);

    List<ShortPartElementDto> getAll(Long id);

    void delete(Long id);
}