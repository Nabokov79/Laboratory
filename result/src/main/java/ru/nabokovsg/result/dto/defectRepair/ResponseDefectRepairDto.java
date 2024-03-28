package ru.nabokovsg.result.dto.defectRepair;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.result.models.ParameterMeasurement;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Расчитанные результаты измерения параметров выполненного ремонта дефекта элемента, подэлемента")
public class ResponseDefectRepairDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Наименование ремонта")
    private String repairName;
    @Schema(description = "Наименование параметров")
    private Set<ParameterMeasurement> parametersMeasurements;
}