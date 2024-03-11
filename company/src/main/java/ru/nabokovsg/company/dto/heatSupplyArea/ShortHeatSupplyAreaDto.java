package ru.nabokovsg.company.dto.heatSupplyArea;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.company.dto.divisionContact.FullDivisionContact;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Краткие данные района теплоснабжения")
public class ShortHeatSupplyAreaDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное наименование")
    private String fullName;
    @Schema(description = "Краткое наименование")
    private String shortName;
    @Schema(description = "Контакты сотрудника райна теплоснабжения")
    private FullDivisionContact contact;
}