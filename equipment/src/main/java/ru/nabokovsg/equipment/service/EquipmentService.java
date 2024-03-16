package ru.nabokovsg.equipment.service;

import ru.nabokovsg.equipment.dto.equipments.EquipmentDto;
import ru.nabokovsg.equipment.dto.equipments.ResponseEquipmentDto;
import ru.nabokovsg.equipment.models.Equipment;
import java.util.List;

public interface EquipmentService {

    ResponseEquipmentDto save(EquipmentDto equipmentDto);

    ResponseEquipmentDto update(EquipmentDto equipmentDto);

    ResponseEquipmentDto get(Long id);

    List<ResponseEquipmentDto> getAll(Long id);

    void delete(Long id);

    Equipment getById(Long id);
}