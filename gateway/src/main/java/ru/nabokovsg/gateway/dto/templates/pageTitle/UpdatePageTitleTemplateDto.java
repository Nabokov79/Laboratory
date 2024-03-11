package ru.nabokovsg.gateway.dto.templates.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Измененные данные титульного листа")
public class UpdatePageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Индентификатор типа документа")
    @NotNull(message = "header document id should not be null")
    @Positive(message = "header document id must be positive")
    private Long headerDocumentId;
    @Schema(description = "Индентификатор типа оборудования")
    @NotNull(message = "equipment type id should not be null")
    @Positive(message = "equipment type id must be positive")
    private Long equipmentTypeId;
    @Schema(description = "Строка наименования оборудования")
    @NotBlank(message = "equipment should not be blank")
    private String equipment;
    @Schema(description = "Строка наименования места расположения оборудования")
    @NotBlank(message = "installationLocation should not be blank")
    private String installationLocation;
    @Schema(description = "Строка указания адреса")
    @NotBlank(message = "address should not be blank")
    private String address;
    @Schema(description = "Индентификатор сотрудника")
    @NotNull(message = "employee id should not be null")
    @Positive(message = "employee id must be positive")
    private Long employeeId;
    @Schema(description = "Населенный пункт")
    @NotBlank(message = "city should not be blank")
    private String city;
}