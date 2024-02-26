package ru.nabokovsg.equipment.service;

import ru.nabokovsg.equipment.dto.equipments.EquipmentDto;
import ru.nabokovsg.equipment.dto.equipmentType.FullEquipmentTypeDto;
import ru.nabokovsg.equipment.models.EquipmentType;

import java.util.List;

public interface EquipmentTypeService {

    EquipmentType create(EquipmentDto equipmentDto);

    List<FullEquipmentTypeDto> getAll();
}