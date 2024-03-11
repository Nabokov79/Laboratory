package ru.nabokovsg.result.dto.repairMethod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.result.dto.sizeParameters.SizeParametersDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового способа ремонта")
public class RepairMethodDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор ипа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Название способа ремонта")
    private String methodName;
    @Schema(description = "Параметры дефекта")
    private List<SizeParametersDto> parameters;
}