package ru.nabokovsg.gateway.dto.templates.protocols;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные изменения информацииив шаблоне протокола/заключения")
public class UpdateProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор типа оборудования")
    @NotNull(message = "equipment type id should not be null")
    @Positive(message = "equipment type id can only be positive")
    private Long equipmentTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    @NotNull(message = "header document id should not be null")
    @Positive(message = "header document id can only be positive")
    private Long headerDocumentId;
}