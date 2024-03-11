package ru.nabokovsg.template.dto.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/измененния данных титульного листа")
public class PageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор типа документа")
    private Long headerDocumentId;
    @Schema(description = "Индентификатор типа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Строка наименования оборудования")
    private String equipment;
    @Schema(description = "Строка наименования места расположения оборудования")
    private String installationLocation;
    @Schema(description = "Строка указания адреса")
    private String address;
    @Schema(description = "Индентификатор сотрудника")
    private Long employeeId;
    @Schema(description = "Населенный пункт")
    private String city;
}