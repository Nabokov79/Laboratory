package ru.nabokovsg.company.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.address.ResponseAddressDto;
import ru.nabokovsg.company.dto.divisionContact.ResponseDivisionContact;
import ru.nabokovsg.company.dto.licenses.ResponseLicenseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные подразделения")
public class ResponseDepartmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String fullName;
    @Schema(description = "Краткое название")
    private String shortName;
    @Schema(description = "Адрес")
    private ResponseAddressDto address;
    @Schema(description = "Контакты сотрудника подразделения")
    private ResponseDivisionContact contact;
    @Schema(description = "Лицензия/аттеставция подразделения")
    private List<ResponseLicenseDto> licenses;
}