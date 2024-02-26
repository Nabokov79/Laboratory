package ru.nabokovsg.equipment.service;

import ru.nabokovsg.equipment.dto.passport.EquipmentPassportDto;
import ru.nabokovsg.equipment.dto.passport.FullEquipmentPassportDto;

public interface EquipmentPassportService {

    FullEquipmentPassportDto save(EquipmentPassportDto passportDto);

    FullEquipmentPassportDto update(EquipmentPassportDto passportDto);

    void delete(Long id);
}