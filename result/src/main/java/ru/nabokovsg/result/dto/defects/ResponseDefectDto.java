package ru.nabokovsg.result.dto.defects;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.result.dto.parameters.ResponseParametersDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные дефекта")
public class ResponseDefectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название дефекта")
    private String defectName;
    @Schema(description = "Параметры дефекта")
    private List<ResponseParametersDto> parameters;
}