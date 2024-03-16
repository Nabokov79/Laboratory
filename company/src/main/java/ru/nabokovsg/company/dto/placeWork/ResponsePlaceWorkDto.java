package ru.nabokovsg.company.dto.placeWork;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.branch.ShortResponseBranchDto;
import ru.nabokovsg.company.dto.building.ShortResponseBuildingDto;
import ru.nabokovsg.company.dto.department.ShortResponseDepartmentDto;
import ru.nabokovsg.company.dto.exploitationRegion.ShortResponseExploitationRegionDto;
import ru.nabokovsg.company.dto.organization.ShortResponseOrganizationDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные места работы сотрудника")
public class ResponsePlaceWorkDto {

    @Schema(description = "Организация")
    private ShortResponseOrganizationDto organization;
    @Schema(description = "Филиал организации")
    private ShortResponseBranchDto branch;
    @Schema(description = "Подразделение филиала организации")
    private ShortResponseDepartmentDto department;
    @Schema(description = "Эксплуатационный участок")
    private ShortResponseExploitationRegionDto exploitationRegion;
    @Schema(description = "Эксплуатационный участок")
    private ShortResponseBuildingDto building;
}