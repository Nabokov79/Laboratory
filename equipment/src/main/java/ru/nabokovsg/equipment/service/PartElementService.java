package ru.nabokovsg.equipment.service;

import ru.nabokovsg.equipment.dto.partElement.ResponsePartElementDto;
import ru.nabokovsg.equipment.dto.partElement.PartElementDto;
import ru.nabokovsg.equipment.dto.partElement.ShortResponsePartElementDto;

import java.util.List;

public interface PartElementService {

    ResponsePartElementDto save(PartElementDto partElementDto);

    ResponsePartElementDto update(PartElementDto partElementDto);

    ResponsePartElementDto get(Long id);

    List<ShortResponsePartElementDto> getAll(Long id);

    void delete(Long id);
}