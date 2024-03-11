package ru.nabokovsg.template.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.template.dto.header.FullHeaderTemplateDto;
import ru.nabokovsg.template.models.DivisionData;
import ru.nabokovsg.template.models.HeaderTemplate;

@Mapper(componentModel = "spring")
public interface HeaderTemplateMapper {

    @Mapping(source = "divisionData", target = "organization")
    @Mapping(source = "id", target = "id")
    HeaderTemplate mapOrganizationWithHeaderTemplate(Long id, DivisionData divisionData);

    @Mapping(source = "divisionData", target = "branch")
    @Mapping(source = "id", target = "id")
    HeaderTemplate mapBranchWithHeaderTemplate(Long id, DivisionData divisionData);

    @Mapping(source = "divisionData", target = "department")
    @Mapping(source = "id", target = "id")
    HeaderTemplate mapDepartmentWithHeaderTemplate(Long id, DivisionData divisionData);

    @Mapping(source = "divisionData", target = "heatSupplyArea")
    @Mapping(source = "id", target = "id")
    HeaderTemplate mapHeatSupplyAreaWithHeaderTemplate(Long id, DivisionData divisionData);

    @Mapping(source = "divisionData", target = "exploitationRegion")
    @Mapping(source = "id", target = "id")
    HeaderTemplate mapExploitationRegionWithHeaderTemplate(Long id, DivisionData divisionData);

    FullHeaderTemplateDto mapToFullHeaderTemplateDto(HeaderTemplate headerTemplate);
}