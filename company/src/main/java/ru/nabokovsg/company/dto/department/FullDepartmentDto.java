package ru.nabokovsg.company.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.address.FullAddressDto;
import ru.nabokovsg.company.dto.divisionContact.FullDivisionContact;
import ru.nabokovsg.company.dto.licenses.FullLicenseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные подразделения")
public class FullDepartmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String fullName;
    @Schema(description = "Краткое название")
    private String shortName;
    @Schema(description = "Адрес")
    private FullAddressDto address;
    @Schema(description = "Контакты сотрудника подразделения")
    private FullDivisionContact contact;
    @Schema(description = "Лицензия/аттеставция подразделения")
    private List<FullLicenseDto> licenses;
}