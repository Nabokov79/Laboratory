package ru.nabokovsg.result.dto.sizeParameters;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные параметра дефекта")
public class FullSizeParametersDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название параметра дефекта")
    private String parametersName;
    @Schema(description = "Единица измерения параметра дефекта")
    private String unitMeasurement;
}