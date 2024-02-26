package ru.nabokovsg.company.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.address.FullAddressDto;
import ru.nabokovsg.company.dto.department.ShortDepartmentDto;

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
    @Schema(description = "Подразделения")
    private List<ShortDepartmentDto> departments;
}