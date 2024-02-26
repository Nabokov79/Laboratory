package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.company.models.*;

@Mapper(componentModel = "spring")
public interface PlaceWorkMapper {

    @Mapping(source = "organization", target = "organization")
    @Mapping(source = "branch", target = "branch")
    @Mapping(target = "id", ignore = true)
    PlaceWork mapToNewPlaceWork(Organization organization, Branch branch);

    @Mapping(source = "organization", target = "organization")
    @Mapping(source = "branch", target = "branch")
    @Mapping(source = "id", target = "id")
    PlaceWork mapToUpdatePlaceWork(Long id, Organization organization, Branch branch);

    @Mapping(source = "department", target = "department")
    void mapFromDepartment(@MappingTarget PlaceWork placeWork, Department department);
    @Mapping(source = "exploitationRegion", target = "exploitationRegion")
    void mapFromExploitationRegion(@MappingTarget PlaceWork placeWork, ExploitationRegion exploitationRegion);

    @Mapping(source = "building", target = "building")
    void mapFromBuilding(@MappingTarget PlaceWork placeWork, Building building);
}