package ru.nabokovsg.gateway.dto.equipment_service.equipmentPassport;

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
@Schema(description = "Данные для изменения информации в паспорте оборудования")
public class UpdateEquipmentPassportDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
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