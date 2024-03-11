package ru.nabokovsg.result.dto.repairMethod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.result.dto.sizeParameters.FullSizeParametersDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные способа ремонта")
public class FullRepairMethodDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название способа ремонта")
    private String methodName;
    @Schema(description = "Параметры дефекта")
    private List<FullSizeParametersDto> parameters;
}