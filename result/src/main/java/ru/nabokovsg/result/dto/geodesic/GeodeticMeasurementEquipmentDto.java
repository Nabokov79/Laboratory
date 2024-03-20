package ru.nabokovsg.result.dto.geodesic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Результаты выполнения геодезии(нивелировании)")
public class GeodeticMeasurementEquipmentDto {

    @Schema(description = "Индентификатор записи журнала задач")
    private Long id;
    @Schema(description = "Индентификатор типа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Индентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Пустой или полный бак-аккумулятор")
    private Boolean full;
    @Schema(description = "Результаты выполнения геодезии(нивелировании)")
    private List<GeodesicMeasurementDto> measurements;
}