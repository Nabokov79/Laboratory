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
@Schema(description = "Значение параметра дефекта элемента, подэлемента оборудования")
public class ParametersDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор дефекта")
    private Long parameterId;
    @Schema(description = "Измеренное значение параметра")
    private double parameterValue;
}