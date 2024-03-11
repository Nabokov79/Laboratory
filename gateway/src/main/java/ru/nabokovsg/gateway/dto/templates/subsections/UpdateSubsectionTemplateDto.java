package ru.nabokovsg.gateway.dto.templates.subsections;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации в подразделе")
public class UpdateSubsectionTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Наименование подраздела")
    @NotBlank(message = "subsection name should not be blank")
    private String subsectionName;
    @Schema(description = "Порядковый номер подраздела")
    @NotNull(message = "sequential subsection number should not be null")
    @Positive(message = "sequential subsection number can only be positive")
    private Double sequentialNumber;
    @Schema(description = "Текст пользователя")
    private String userText;
    @Schema(description = "Показать номер подраздела в документе")
    @NotNull(message = "sequential number visible should not be null")
    private boolean sequentialNumberVisible;
    @Schema(description = "Тип данных подраздела")
    @NotBlank(message = "subsection data type should not be blank")
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