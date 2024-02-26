package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.building.BuildingDto;
import ru.nabokovsg.company.dto.building.FullBuildingDto;
import ru.nabokovsg.company.dto.building.ShortBuildingDto;
import ru.nabokovsg.company.models.Building;

import java.util.List;

public interface BuildingService {

    ShortBuildingDto save(BuildingDto buildingDto);

    ShortBuildingDto update(BuildingDto buildingDto);

    FullBuildingDto get(Long id);

    Building getById(Long id);

    List<ShortBuildingDto> getAll(Long id);

    void  delete(Long id);
}