package ru.nabokovsg.template.dto.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.template.dto.header.HeaderTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/измененния данных титульного листа")
public class PageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор типа объекта")
    private Long objectTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    private Long reportingDocumentId;
    @Schema(description = "Строка наименования объекта")
    private String object;
    @Schema(description = "Строка наименования места расположения объекта")
    private String installationLocation;
    @Schema(description = "Строка указания адреса")
    private String address;
    @Schema(description = "Индентификатор сотрудника")
    private Long employeeId;
    @Schema(description = "Населенный пункт")
    private String city;
    @Schema(description = "Заголовок титульного листа")
    private HeaderTemplateDto header;
}