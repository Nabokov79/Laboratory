package ru.nabokovsg.result.dto.сontrolPoint;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные расчетов измерений контрольных точек(геодезия)")
public class ControlPointMeasurementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Рассчитанные значения измерений контрольных точек")
    private List<ControlPointDto> controlPoints;
    @Schema(description = "Рассчитанные значения для соседних и диаметрально противоположных точек измерения")
    private List<PointDifferenceDto> pointDifferences;
}