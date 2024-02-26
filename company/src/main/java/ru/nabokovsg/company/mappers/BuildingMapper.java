package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.company.dto.building.BuildingDto;
import ru.nabokovsg.company.dto.building.FullBuildingDto;
import ru.nabokovsg.company.dto.building.ShortBuildingDto;
import ru.nabokovsg.company.models.Address;
import ru.nabokovsg.company.models.Building;
import ru.nabokovsg.company.models.ExploitationRegion;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    @Mapping(source = "address", target = "address")
    @Mapping(source = "exploitationRegion", target = "exploitationRegion")
    @Mapping(target = "id", ignore = true)
    Building mapToNewBuilding(BuildingDto buildingDto, Address address, ExploitationRegion exploitationRegion);

    @Mapping(source = "address", target = "address")
    @Mapping(source = "exploitationRegion", target = "exploitationRegion")
    @Mapping(source = "buildingDto.id", target = "id")
    Building mapToUpdateBuilding(BuildingDto buildingDto, Address address, ExploitationRegion exploitationRegion);

    FullBuildingDto mapToFullBuildingDto(Building building);

    ShortBuildingDto mapToShortBuildingDto(Building building);
}