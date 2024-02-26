package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.heatSupplyArea.FullHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.HeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ShortHeatSupplyAreaDto;
import ru.nabokovsg.company.models.HeatSupplyArea;

import java.util.List;

public interface HeatSupplyAreaService {

    ShortHeatSupplyAreaDto save(HeatSupplyAreaDto areaDto);

    ShortHeatSupplyAreaDto update(HeatSupplyAreaDto areaDto);

    FullHeatSupplyAreaDto get(Long id);

    HeatSupplyArea getById(Long id);

    List<ShortHeatSupplyAreaDto> getAll(Long id);

    void delete(Long id);
}