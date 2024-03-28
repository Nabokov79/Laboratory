package ru.nabokovsg.result.dto.parameters;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Рассчитанные параметры дефекта")
public class ResponseParameterMeasurementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название параметра дефекта")
    private String parametersName;
    @Schema(description = "Значение параметра, не подлежащего рассчету")
    private Double parametersValue;
    @Schema(description = "Минимальное значение параметра")
    private Double minValue;
    @Schema(description = "Максимальное значение параметра")
    private Double maxValue;
    @Schema(description = "Единица измерения параметра")
    private String unitMeasurement;
}
