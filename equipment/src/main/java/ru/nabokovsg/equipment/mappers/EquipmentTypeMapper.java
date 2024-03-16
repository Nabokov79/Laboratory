package ru.nabokovsg.equipment.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.equipment.dto.equipments.EquipmentDto;
import ru.nabokovsg.equipment.dto.equipmentType.ResponseEquipmentTypeDto;
import ru.nabokovsg.equipment.models.EquipmentType;

@Mapper(componentModel = "spring")
public interface EquipmentTypeMapper {

    EquipmentType mapToEquipmentType(EquipmentDto equipmentDto);

    ResponseEquipmentTypeDto mapFullEquipmentTypeDto(EquipmentType equipmentType);
}