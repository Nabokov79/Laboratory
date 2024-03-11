package ru.nabokovsg.template.dto.protocolReport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения шаблона заголовка протокола отчета")
public class ProtocolReportTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор типа документа")
    private Long headerDocumentId;
    @Schema(description = "Индентификатор раздела")
    private Long sectionId;
    @Schema(description = "Порядковый номер протокола")
    private Integer sequentialNumber;
    @Schema(description = "Текст пользователя после заголовка")
    private String userTextAfterHeading;
}