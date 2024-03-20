package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.result.models.DeviationYear;
import ru.nabokovsg.result.models.ReferencePoint;

@Mapper(componentModel = "spring")
public interface DeviationYearMapper {

    @Mapping(source = "year", target = "year")
    @Mapping(source = "deviation", target = "deviation")
    @Mapping(source = "referencePoint", target = "referencePoint")
    @Mapping(target = "id", ignore = true)
    DeviationYear mapToDeviationYear(Integer year, Integer deviation, ReferencePoint referencePoint);

    @Mapping(source = "year", target = "year")
    @Mapping(source = "deviation", target = "deviation")
    @Mapping(source = "referencePoint", target = "referencePoint")
    @Mapping(source = "id", target = "id")
    DeviationYear mapToUpdateDeviationYear(Integer year, Integer deviation, ReferencePoint referencePoint, Long id);
}