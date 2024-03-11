package ru.nabokovsg.template.dto.subsection;

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
@Schema(description = "Данные для изменения информации в подразделе")
public class SubsectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип части шаблона документа")
    private String templateType;
    @Schema(description = "Индентификатор части шаблона документа")
    private Long templateId;
    @Schema(description = "Наименование подраздела")
    private String subsectionName;
    @Schema(description = "Порядковый номер подраздела")
    private Double sequentialNumber;
    @Schema(description = "Текст пользователя")
    private String userText;
    @Schema(description = "Показать номер подраздела в документе")
    private boolean sequentialNumberVisible;
    @Schema(description = "Тип данных подраздела")
    private String subsectionDataType;
    @Schema(description = "Тип структурного подразделения")
    private String divisionType;
    @Schema(description = "Индентификатор структурного подразделения организации")
    private Long divisionId;
    @Schema(description = "Пользовательское название структурного подразделения организации")
    private String userDivisionName;
    @Schema(description = "Указать адресс структурного подразделения")
    private Boolean address;
    @Schema(description = "Указать лицензию/аттестацию структурного подразделения")
    private Boolean license;
    @Schema(description = "Индентификатор сотрудника")
    private Long employeeId;
    @Schema(description = "Индентификаторы нормативно-технической документации")
    private List<Long> documentationIds;
    @Schema(description = "Индентификаторы средств контроля и измерений")
    private List<Long> measuringToolIds;
    @Schema(description = "Тип документа для автоматического сбора информации")
    private String typeDocument;
    @Schema(description = "Вставить заключение из протокола")
    private boolean protocolConclusion;
    @Schema(description = "Составить сводную таблицу по данным протокола")
    private boolean autoTable;
}