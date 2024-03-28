package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.result.dto.repairMethod.ResponseCompletedRepairsDto;
import ru.nabokovsg.result.dto.repairMethod.CompletedRepairsDto;
import ru.nabokovsg.result.models.CompletedRepairs;

@Mapper(componentModel = "spring")
public interface CompletedRepairsMapper {

    CompletedRepairs mapToRepairMethod(CompletedRepairsDto methodDto);

    ResponseCompletedRepairsDto mapToFullRepairMethod(CompletedRepairs method);
}