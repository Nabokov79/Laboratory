package ru.nabokovsg.gateway.dto.templates.reportProtocol;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации в шаблоне протокола отчета")
public class UpdateProtocolReportTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор типа документа")
    @NotNull(message = "header document id should not be null")
    @Positive(message = "header document id can only be positive")
    private Long headerDocumentId;
    @Schema(description = "Порядковый номер протокола")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Текст пользователя после заголовка")
    private String userTextAfterHeading;
}