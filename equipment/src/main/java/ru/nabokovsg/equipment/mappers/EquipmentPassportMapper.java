package ru.nabokovsg.equipment.mappers;

import org.mapstruct.Mapping;
import ru.nabokovsg.equipment.dto.passport.EquipmentPassportDto;
import ru.nabokovsg.equipment.dto.passport.ResponseEquipmentPassportDto;
import ru.nabokovsg.equipment.models.Equipment;
import ru.nabokovsg.equipment.models.EquipmentPassport;

public interface EquipmentPassportMapper {

    @Mapping(source = "passportDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "passportDto.header", target = "header")
    @Mapping(source = "passportDto.meaning", target = "meaning")
    @Mapping(source = "equipment", target = "equipment")
    @Mapping(source = "passportDto.id", target = "id")
    EquipmentPassport mapToEquipmentPassport(EquipmentPassportDto passportDto, Equipment equipment);

    ResponseEquipmentPassportDto mapToFullEquipmentPassportDto(EquipmentPassport passport);
}