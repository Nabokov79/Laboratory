package ru.nabokovsg.result.dto.parameters;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Параметр дефекта элемента, подэлемента оборудования")
public class ParametersDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название параметра дефекта")
    private String parameterName;
    @Schema(description = "Единица измерения параметра дефекта")
    private String unitMeasurement;
    @Schema(description = "Использовать для рассчета остаточной толщины")
    private Boolean useCalculateThickness;
    @Schema(description = "Вычислить максимальное значение параметра элемента, подэлемента")
    private boolean max;
    @Schema(description = "Вычислить минимальное значение параметра элемента, подэлемента")
    private boolean min;
    @Schema(description = "Вычислить максимальное и минимальное значение параметра элемента, подэлемента")
    private boolean maxAndMin;
    @Schema(description = "Подсчитать количество параметра элемента, подэлемента")
    private boolean quantity;
}