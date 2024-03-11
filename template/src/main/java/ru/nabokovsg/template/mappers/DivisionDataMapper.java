package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.template.client.dto.DivisionDto;
import ru.nabokovsg.template.models.DivisionData;

@Mapper(componentModel = "spring")
public interface DivisionDataMapper {

    @Mapping(source = "name", target = "divisionName")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "license", target = "license")
    @Mapping(source = "contact", target = "contact")
    @Mapping(target = "id", ignore = true)
    DivisionData mapToDivisionData(String name, String address, String license, String contact);

    @Mapping(source = "division.divisionName", target = "divisionName")
    @Mapping(source = "division.address", target = "address")
    @Mapping(source = "division.license", target = "license")
    @Mapping(source = "division.contact", target = "contact")
    @Mapping(source = "divisionId", target = "id")
    DivisionData mapToUpdateDivisionData(Long divisionId, DivisionData division);
}