package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.company.dto.heatSupplyArea.ResponseHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.HeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ShortResponseHeatSupplyAreaDto;
import ru.nabokovsg.company.models.Branch;
import ru.nabokovsg.company.models.DivisionContact;
import ru.nabokovsg.company.models.HeatSupplyArea;

@Mapper(componentModel = "spring")
public interface HeatSupplyAreaMapper {

    @Mapping(source = "areaDto.fullName", target = "fullName")
    @Mapping(source = "areaDto.shortName", target = "shortName")
    @Mapping(source = "divisionContact", target = "contact")
    @Mapping(source = "branch", target = "branch")
    @Mapping(source = "areaDto.id", target = "id")
    HeatSupplyArea mapToHeatSupplyArea(HeatSupplyAreaDto areaDto
                                    , Branch branch
                                    , DivisionContact divisionContact);

    ResponseHeatSupplyAreaDto mapToFullHeatSupplyAreaDto(HeatSupplyArea heatSupplyArea);

    ShortResponseHeatSupplyAreaDto mapToShortHeatSupplyAreaDto(HeatSupplyArea heatSupplyArea);
}