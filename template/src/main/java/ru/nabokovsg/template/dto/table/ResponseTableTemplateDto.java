package ru.nabokovsg.template.dto.table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.dto.columns.ResponseColumnHeaderTemplateDto;
import ru.nabokovsg.template.models.enums.TableDataType;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные шаблона таблицы")
public class ResponseTableTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип данных таблицы")
    private TableDataType tableDataType;
    @Schema(description = "Порядковый номер таблицы")
    private Integer sequentialNumber;
    @Schema(description = "Название таблицы")
    private String tableName;
    @Schema(description = "Текст перед таблицей")
    private String textBeforeTable;
    @Schema(description = "Текст после таблицы")
    private String textAfterTable;
    @Schema(description = "Шаблоны колонок таблицы")
    private List<ResponseColumnHeaderTemplateDto> columnHeaders;
}