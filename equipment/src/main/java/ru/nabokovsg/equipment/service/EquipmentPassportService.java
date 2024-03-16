package ru.nabokovsg.equipment.service;

import ru.nabokovsg.equipment.dto.passport.EquipmentPassportDto;
import ru.nabokovsg.equipment.dto.passport.ResponseEquipmentPassportDto;

public interface EquipmentPassportService {

    ResponseEquipmentPassportDto save(EquipmentPassportDto passportDto);

    ResponseEquipmentPassportDto update(EquipmentPassportDto passportDto);

    void delete(Long id);
}