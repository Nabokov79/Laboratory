package ru.nabokovsg.gateway.dto.templates.sections;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления разделов документа")
public class SectionDataTemplateDto {

    @Schema(description = "Тип документа")
    @NotNull(message = "header document id should not be null")
    @Positive(message = "header document id must be positive")
    private Long headerDocumentId;
    @Schema(description = "Индентификатор типа оборудования")
    @NotNull(message = "equipment type id should not be null")
    @Positive(message = "equipment type id must be positive")
    private Long equipmentTypeId;
    @Schema(description = "Данные разделов шаблона документа")
    private List<@Valid NewSectionTemplateDto> sections;
}