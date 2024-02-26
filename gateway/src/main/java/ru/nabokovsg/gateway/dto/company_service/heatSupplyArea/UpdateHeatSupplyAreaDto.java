package ru.nabokovsg.gateway.dto.company_service.heatSupplyArea;

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
@Schema(description = "Данные для изменения информации о районе теплоснабжения")
public class UpdateHeatSupplyAreaDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Полное наименование")
    @NotBlank(message = "full name heat supply area should not be blank")
    private String fullName;
    @Schema(description = "Краткое наименование")
    @NotBlank(message = "short name  heat supply area should not be blank")
    private String shortName;
}