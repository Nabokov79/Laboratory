package ru.nabokovsg.result.dto.defectMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.result.dto.parameters.ParametersDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Результаты измерения дефекта элемента, подэлемента оборудования")
public class DefectMeasurementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Индентификатор дефекта")
    private Long defectId;
    @Schema(description = "Измеренные параметры дефекта")
    private List<ParametersDto> parameters;
}