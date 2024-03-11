package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.template.client.dto.MeasuringToolDto;
import ru.nabokovsg.template.models.MeasuringToolTemplate;

@Mapper(componentModel = "spring")
public interface MeasuringToolMapper {

    @Mapping(source = "toll", target = "value")
    @Mapping(source = "id", target = "id")
    MeasuringToolTemplate mapToMeasuringToolTemplate(MeasuringToolDto measuringToolDto);
}