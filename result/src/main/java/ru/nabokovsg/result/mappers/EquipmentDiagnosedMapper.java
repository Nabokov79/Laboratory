package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.result.dto.equipmentDiagnosed.EquipmentDiagnosedDto;
import ru.nabokovsg.result.models.EquipmentDiagnosed;

@Mapper(componentModel = "spring")
public interface EquipmentDiagnosedMapper {

    @Mapping(source = "equipmentDto.id", target = "taskJournalId")
    @Mapping(source = "equipmentDto.equipmentId", target = "equipmentId")
    @Mapping(source = "equipmentDto.full", target = "full")
    @Mapping(source = "equipmentDto.equipmentOld", target = "equipmentOld")
    @Mapping(target = "id", ignore = true)
    EquipmentDiagnosed mapToEquipmentDiagnosed(EquipmentDiagnosedDto equipmentDto);

    @Mapping(source = "full", target = "full")
    EquipmentDiagnosed mapToParamFull(@MappingTarget EquipmentDiagnosed equipment, Boolean full);
}