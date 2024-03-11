package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.company.dto.building.BuildingDto;
import ru.nabokovsg.company.dto.building.FullBuildingDto;
import ru.nabokovsg.company.dto.building.ShortBuildingDto;
import ru.nabokovsg.company.models.Address;
import ru.nabokovsg.company.models.Building;
import ru.nabokovsg.company.models.DivisionContact;
import ru.nabokovsg.company.models.ExploitationRegion;
import ru.nabokovsg.company.models.enums.BuildingType;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    @Mapping(source = "buildingDto.login", target = "login")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "buildingType", target = "buildingType")
    @Mapping(source = "exploitationRegion", target = "exploitationRegion")
    @Mapping(source = "divisionContact", target = "contact")
    @Mapping(source = "buildingDto.id", target = "id")
    Building mapToBuilding(BuildingDto buildingDto, DivisionContact divisionContact, BuildingType buildingType
                         , Address address, ExploitationRegion exploitationRegion);

    FullBuildingDto mapToFullBuildingDto(Building building);

    ShortBuildingDto mapToShortBuildingDto(Building building);
}