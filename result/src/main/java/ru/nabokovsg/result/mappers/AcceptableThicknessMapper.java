package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.result.dto.acceptable.AcceptableThicknessDto;
import ru.nabokovsg.result.dto.acceptable.FullAcceptableThicknessDto;
import ru.nabokovsg.result.models.AcceptableThickness;

@Mapper(componentModel = "spring")
public interface AcceptableThicknessMapper {

    AcceptableThickness mapToAcceptableThickness(AcceptableThicknessDto thicknessDto);

    FullAcceptableThicknessDto mapToFullAcceptableThicknessDto(AcceptableThickness thickness);
}