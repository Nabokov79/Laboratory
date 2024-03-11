package ru.nabokovsg.result.dto.geodesy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные значений норм для проведения геодезии(нивелирования)")
public class FullPermissibleDeviationsGeodesyDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Индентификатор типа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Полное или пустое оборудование")
    private Boolean full;
    @Schema(description = "Старое или новое оборудование")
    private Boolean old;
    @Schema(description = "Максимальная допустимая осадка")
    private Integer acceptablePrecipitation;
    @Schema(description = "Максимальная допустимая разность для соседних точек)")
    private Integer maxDifferenceNeighboringPoints;
    @Schema(description = "Максимальная допустимая разность для диаметральных точек")
    private Integer maxDifferenceDiametricPoints;
    @Schema(description = "Допустимая погрешность измерения")
    private Integer measurementError;
    @Schema(description = "Колличество мест проведения измерений")
    private Integer numberMeasurements;
}