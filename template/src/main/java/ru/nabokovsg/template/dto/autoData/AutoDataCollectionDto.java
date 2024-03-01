package ru.nabokovsg.template.dto.autoData;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для автоматического сбора информации")
public class AutoDataCollectionDto {

    @Schema(description = "Индентификатор протокола/заключения, протокола в составе отчета")
    private Long protocolId;
    @Schema(description = "Добавить заключение из протокола")
    private boolean protocolConclusion;
    @Schema(description = "Составить сводную таблицу по данным таблицы протокола")
    private boolean autoTable;
}