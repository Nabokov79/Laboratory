package ru.nabokovsg.company.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.address.ResponseAddressDto;
import ru.nabokovsg.company.dto.department.ShortResponseDepartmentDto;
import ru.nabokovsg.company.dto.divisionContact.ResponseDivisionContact;
import ru.nabokovsg.company.dto.exploitationRegion.ResponseExploitationRegionDto;
import ru.nabokovsg.company.dto.licenses.ResponseLicenseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные филиала")
public class ResponseBranchDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String fullName;
    @Schema(description = "Краткое название")
    private String shortName;
    @Schema(description = "Адрес")
    private ResponseAddressDto address;
    @Schema(description = "Контакты сотрудника филиала")
    private ResponseDivisionContact contact;
    @Schema(description = "Лицензия/аттеставция филиал")
    private List<ResponseLicenseDto> licenses;
    @Schema(description = "Подразделения")
    private List<ShortResponseDepartmentDto> departments;
    @Schema(description = "Подразделения")
    private List<ResponseExploitationRegionDto> heatSupplyAreas;
}