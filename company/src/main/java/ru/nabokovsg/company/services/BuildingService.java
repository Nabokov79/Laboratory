package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.building.BuildingDto;
import ru.nabokovsg.company.dto.building.ResponseBuildingDto;
import ru.nabokovsg.company.dto.building.ShortResponseBuildingDto;
import ru.nabokovsg.company.models.Building;

import java.util.List;

public interface BuildingService {

    ShortResponseBuildingDto save(BuildingDto buildingDto);

    ShortResponseBuildingDto update(BuildingDto buildingDto);

    ResponseBuildingDto get(Long id);

    Building getById(Long id);

    List<ShortResponseBuildingDto> getAll(Long id);

    void  delete(Long id);
}