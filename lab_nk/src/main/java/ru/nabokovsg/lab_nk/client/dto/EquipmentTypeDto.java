package ru.nabokovsg.lab_nk.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные типа оборудования")
public class EquipmentTypeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Наименование")
    @NotBlank(message = "equipment full name should not be blank")
    private String equipmentName;
    @Schema(description = "Модель")
    private String model;
}