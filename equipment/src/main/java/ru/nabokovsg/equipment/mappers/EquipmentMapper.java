package ru.nabokovsg.equipment.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.equipment.dto.equipments.EquipmentDto;
import ru.nabokovsg.equipment.dto.equipments.FullEquipmentDto;
import ru.nabokovsg.equipment.models.Equipment;
import ru.nabokovsg.equipment.models.EquipmentType;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    @Mapping(source = "equipmentType", target = "equipmentType")
    @Mapping(source = "equipmentDto.buildingId", target = "buildingId")
    @Mapping(source = "equipmentDto.equipmentName", target = "equipmentName")
    @Mapping(source = "equipmentDto.stationaryNumber", target = "stationaryNumber")
    @Mapping(source = "equipmentDto.volume", target = "volume")
    @Mapping(source = "equipmentDto.model", target = "model")
    @Mapping(source = "equipmentDto.id", target = "id")
    Equipment mapToEquipment(EquipmentDto equipmentDto, EquipmentType equipmentType);

    FullEquipmentDto mapToFullEquipmentDto(Equipment equipment);
}