package ru.nabokovsg.equipment.service;

import ru.nabokovsg.equipment.dto.equipments.EquipmentDto;
import ru.nabokovsg.equipment.dto.equipments.FullEquipmentDto;
import ru.nabokovsg.equipment.models.Equipment;
import java.util.List;

public interface EquipmentService {

    FullEquipmentDto save(EquipmentDto equipmentDto);

    FullEquipmentDto update(EquipmentDto equipmentDto);

    FullEquipmentDto get(Long id);

    List<FullEquipmentDto> getAll(Long id);

    void delete(Long id);

    Equipment getById(Long id);
}