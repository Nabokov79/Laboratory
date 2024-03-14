package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.client.dto.AddressDto;
import ru.nabokovsg.lab_nk.client.dto.BuildingDto;
import ru.nabokovsg.lab_nk.client.dto.EquipmentDto;

public interface StringBuilderService {

    String buildFromBuilding(BuildingDto buildingDto);

    String buildFromAddress(AddressDto address);

    String getStringEquipment(EquipmentDto equipment);
}