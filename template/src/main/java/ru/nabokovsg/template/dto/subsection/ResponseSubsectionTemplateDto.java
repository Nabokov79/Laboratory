package ru.nabokovsg.template.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.dto.table.ResponseTableTemplateDto;
import ru.nabokovsg.template.models.enums.TypeDocument;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные подраздела")
public class ResponseSubsectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер подраздела")
    private double sequentialNumber;
    @Schema(description = "Название подраздела")
    private String subsectionName;
    @Schema(description = "Текст пользователя")
    private String userText;
    @Schema(description = "Показать номер подраздела в документе")
    private boolean sequentialNumberVisible;
    @Schema(description = "Данные структурного подразделения")
    private String divisionData;
    @Schema(description = "Данные об аттестации сотрудника")
    private String certificateEmployee;
    @Schema(description = "Индентификатор таблицы")
    private ResponseTableTemplateDto table;
    @Schema(description = "Тип документа для автоматического сбора информации")
    private TypeDocument typeDocument;
    @Schema(description = "Заключение протокола(для автоматической вставки)")
    private boolean protocolConclusion;
    @Schema(description = "Сводная таблица протокола")
    private boolean autoTable;
}