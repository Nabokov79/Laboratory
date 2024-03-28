package ru.nabokovsg.result.dto.visualInspection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные результатов визуального осмотра элементов оборудования")
public class VisualInspectionDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор записи журнала задач")
    private Long taskJournalId;
    @Schema(description = "Индентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Результат визуального осмотра элемента")
    private String inspection;
}