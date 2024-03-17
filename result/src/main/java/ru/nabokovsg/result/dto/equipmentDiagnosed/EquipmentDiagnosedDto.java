package ru.nabokovsg.result.dto.equipmentDiagnosed;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные диагностируемого оборудования")
public class EquipmentDiagnosedDto {

    @Schema(description = "Индентификатор записи журнала задач")
    private Long id;
    @Schema(description = "Индентификатор типа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Индентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Пустой или полный бак-аккумулятор")
    private Boolean full;
    @Schema(description = "Старое или новое оборудование(относиться к бакам-аккумуляторам для рассчета данных геодезии)")
    private Boolean equipmentOld;
}