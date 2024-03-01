package ru.nabokovsg.template.dto.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.dto.header.FullHeaderTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Полные данные титульного листа")
public class FullPageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Индентификатор типа объекта")
    private Long objectTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    private Long reportingDocumentId;
    @Schema(description = "Заголовок")
    private FullHeaderTemplateDto header;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String heading;
    @Schema(description = "Строка наименования объекта")
    private String object;
    @Schema(description = "Строка местоположения")
    private String installationLocation;
    @Schema(description = "Строка адреса")
    private String address;
    @Schema(description = "Строка подписи")
    private String signature;
    @Schema(description = "Населенный пункт")
    private String city;
    @Schema(description = "Год")
    private String year;
}