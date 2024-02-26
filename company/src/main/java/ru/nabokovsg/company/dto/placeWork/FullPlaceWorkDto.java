package ru.nabokovsg.company.dto.placeWork;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.branch.ShortBranchDto;
import ru.nabokovsg.company.dto.building.ShortBuildingDto;
import ru.nabokovsg.company.dto.department.ShortDepartmentDto;
import ru.nabokovsg.company.dto.exploitationRegion.ShortExploitationRegionDto;
import ru.nabokovsg.company.dto.organization.ShortOrganizationDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные места работы сотрудника")
public class FullPlaceWorkDto {

    @Schema(description = "Организация")
    private ShortOrganizationDto organization;
    @Schema(description = "Филиал организации")
    private ShortBranchDto branch;
    @Schema(description = "Подразделение филиала организации")
    private ShortDepartmentDto department;
    @Schema(description = "Эксплуатационный участок")
    private ShortExploitationRegionDto exploitationRegion;
    @Schema(description = "Эксплуатационный участок")
    private ShortBuildingDto building;
}