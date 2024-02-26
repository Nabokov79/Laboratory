package ru.nabokovsg.equipment.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.equipment.dto.standardSize.StandardSizeDto;
import ru.nabokovsg.equipment.models.StandardSize;

@Mapper(componentModel = "spring")
public interface StandardSizeMapper {

    StandardSize mapToStandardSize(StandardSizeDto standardSizeDto);
}