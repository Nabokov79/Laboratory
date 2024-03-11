package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.result.dto.repairMethod.FullRepairMethodDto;
import ru.nabokovsg.result.dto.repairMethod.RepairMethodDto;
import ru.nabokovsg.result.models.RepairMethod;

@Mapper(componentModel = "spring")
public interface RepairMethodMapper {

    RepairMethod mapToRepairMethod(RepairMethodDto methodDto);

    FullRepairMethodDto mapToFullRepairMethod(RepairMethod method);
}