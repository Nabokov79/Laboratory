package ru.nabokovsg.company.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.company.dto.divisionContact.ResponseDivisionContact;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Краткие данные подразделения филиала")
public class ShortResponseDepartmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String fullName;
    @Schema(description = "Краткое название")
    private String shortName;
    @Schema(description = "Контакты сотрудника подразделения")
    private ResponseDivisionContact contact;
}