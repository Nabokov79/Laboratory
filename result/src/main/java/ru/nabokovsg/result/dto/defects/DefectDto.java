package ru.nabokovsg.result.dto.defects;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.result.dto.sizeParameters.SizeParametersDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о дефекте")
public class DefectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор ипа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Название дефекта")
    private String defectName;
    @Schema(description = "Параметры дефекта")
    private List<SizeParametersDto> parameters;
}