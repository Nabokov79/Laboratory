package ru.nabokovsg.company.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.address.FullAddressDto;
import ru.nabokovsg.company.dto.branch.ShortBranchDto;
import ru.nabokovsg.company.dto.licenses.FullLicenseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные организации")
public class FullOrganizationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное наименование")
    private String fullName;
    @Schema(description = "Краткое наименование")
    private String shortName;
    @Schema(description = "Адрес")
    private FullAddressDto address;
    @Schema(description = "Лицензия/аттеставция организации")
    private List<FullLicenseDto> licenses;
    @Schema(description = "Филиалы")
    private List<ShortBranchDto> branches;
}