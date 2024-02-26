package ru.nabokovsg.company.dto.placeWork;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные места работы сотрудника")
public class PlaceWorkDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор организации")
    private Long organizationId;
    @Schema(description = "Индентификатор филиала")
    private Long branchId;
    @Schema(description = "Индентификатор подразделения филиала")
    private Long departmentId;
    @Schema(description = "Индентификатор эксплуатационного участка")
    private Long exploitationRegionId;
    @Schema(description = "Индентификатор котельной, ЦТП")
    private Long buildingId;
}