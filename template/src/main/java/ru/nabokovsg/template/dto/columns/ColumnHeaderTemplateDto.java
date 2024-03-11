package ru.nabokovsg.template.dto.columns;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для изменения информации о колонки таблицы")
public class ColumnHeaderTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер колонки")
    private Integer sequentialCellNumber;
    @Schema(description = "Заголовок колонки")
    private String heading;
}