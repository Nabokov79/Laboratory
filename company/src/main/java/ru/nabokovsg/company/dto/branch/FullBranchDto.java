package ru.nabokovsg.company.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.address.FullAddressDto;
import ru.nabokovsg.company.dto.department.ShortDepartmentDto;
import ru.nabokovsg.company.dto.divisionContact.FullDivisionContact;
import ru.nabokovsg.company.dto.exploitationRegion.FullExploitationRegionDto;
import ru.nabokovsg.company.dto.licenses.FullLicenseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные филиала")
public class FullBranchDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String fullName;
    @Schema(description = "Краткое название")
    private String shortName;
    @Schema(description = "Адрес")
    private FullAddressDto address;
    @Schema(description = "Контакты сотрудника филиала")
    private FullDivisionContact contact;
    @Schema(description = "Лицензия/аттеставция филиал")
    private List<FullLicenseDto> licenses;
    @Schema(description = "Подразделения")
    private List<ShortDepartmentDto> departments;
    @Schema(description = "Подразделения")
    private List<FullExploitationRegionDto> heatSupplyAreas;
}