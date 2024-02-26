package ru.nabokovsg.equipment.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.equipment.dto.equipments.EquipmentDto;
import ru.nabokovsg.equipment.dto.equipmentType.FullEquipmentTypeDto;
import ru.nabokovsg.equipment.models.EquipmentType;

@Mapper(componentModel = "spring")
public interface EquipmentTypeMapper {

    EquipmentType mapToEquipmentType(EquipmentDto equipmentDto);

    FullEquipmentTypeDto mapFullEquipmentTypeDto(EquipmentType equipmentType);
}