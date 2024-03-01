package ru.nabokovsg.template.dto.table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.dto.columns.FullColumnHeaderTemplateDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные шаблона таблицы")
public class FullTableTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер таблицы")
    private Integer sequentialNumber;
    @Schema(description = "Название таблицы")
    private String tableName;
    @Schema(description = "Текст перед таблицей")
    private String textBeforeTable;
    @Schema(description = "Текст после таблицы")
    private String textAfterTable;
    @Schema(description = "Шаблоны колонок таблицы")
    private List<FullColumnHeaderTemplateDto> columnHeaders;
}