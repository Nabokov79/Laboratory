package ru.nabokovsg.gateway.dto.templates.tables;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные изменения информации в шаблоне таблицы")
public class UpdateTableTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор части шаблона документа")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long documentPartId;
    @Schema(description = "Тип части шаблона документа")
    @NotBlank(message = "document part type should not be blank")
    private String documentPartType;
    @Schema(description = "Тип данных таблицы")
    @NotBlank(message = "table data type should not be blank")
    private String tableDataType;
    @Schema(description = "Порядковый номер таблицы")
    private Integer sequentialNumber;
    @Schema(description = "Название таблицы")
    @NotBlank(message = "table name should not be blank")
    private String tableName;
    @Schema(description = "Текст перед таблицей")
    private String textBeforeTable;
    @Schema(description = "Текст после таблицы")
    private String textAfterTable;
    @Schema(description = "Шаблоны колонок таблицы")
    @NotNull(message = "column headers should not be null")
    @NotEmpty(message = "column headers should not be empty")
    private List<@Valid UpdateColumnHeaderTemplateDto> columnHeaders;
}