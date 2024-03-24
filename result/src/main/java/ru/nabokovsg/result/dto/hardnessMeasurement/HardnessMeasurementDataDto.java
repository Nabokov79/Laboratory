package ru.nabokovsg.result.dto.hardnessMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Результаты выполнения измерений твердости металла элементов оборудования")
public class HardnessMeasurementDataDto {

    @Schema(description = "Индентификатор записи журнала задач")
    private Long taskJournalId;
    @Schema(description = "Индентификатор типа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Индентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Результаты измерения твердости металла")
    private List<HardnessMeasurementDto> hardnessMeasurements;
}