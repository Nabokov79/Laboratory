package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.company.dto.heatSupplyArea.FullHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.HeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ShortHeatSupplyAreaDto;
import ru.nabokovsg.company.models.HeatSupplyArea;

@Mapper(componentModel = "spring")
public interface HeatSupplyAreaMapper {

    HeatSupplyArea mapToHeatSupplyArea(HeatSupplyAreaDto heatSupplyAreaDto);

    FullHeatSupplyAreaDto mapToFullHeatSupplyAreaDto(HeatSupplyArea heatSupplyArea);

    ShortHeatSupplyAreaDto mapToShortHeatSupplyAreaDto(HeatSupplyArea heatSupplyArea);
}