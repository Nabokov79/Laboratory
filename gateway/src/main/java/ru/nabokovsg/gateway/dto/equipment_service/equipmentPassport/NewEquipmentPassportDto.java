package ru.nabokovsg.gateway.dto.equipment_service.equipmentPassport;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Новые данные паспорта оборудования")
public class NewEquipmentPassportDto {

    @Schema(description = "Индентификатор оборудования")
    @NotNull(message = "equipment id should not be null")
    @Positive(message = "equipment id can only be positive")
    private Long equipmentId;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequentialNumber should not be null")
    @Positive(message = "sequentialNumber can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Наименование")
    @NotBlank(message = "meaning header should not be blank")
    private String header;
    @Schema(description = "Значение")
    @NotBlank(message = "meaning passport should not be blank")
    private String meaning;
}