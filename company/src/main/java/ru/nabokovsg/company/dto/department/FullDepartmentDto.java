package ru.nabokovsg.company.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.address.FullAddressDto;

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
}