package ru.nabokovsg.gateway.dto.templates.protocols;

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
@Schema(description = "Данные нового шаблона протокола/заключения")
public class NewProtocolTemplateDto {

    @Schema(description = "Индентификатор типа оборудования")
    @NotNull(message = "equipment type id should not be null")
    @Positive(message = "equipment type id can only be positive")
    private Long equipmentTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    @NotNull(message = "header document id should not be null")
    @Positive(message = "header document id can only be positive")
    private Long headerDocumentId;
}