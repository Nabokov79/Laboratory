package ru.nabokovsg.gateway.dto.templates.reportProtocol;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового шаблона протокола отчета")
public class NewProtocolReportTemplateDto {

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