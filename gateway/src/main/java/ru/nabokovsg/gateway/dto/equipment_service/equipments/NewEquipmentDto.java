package ru.nabokovsg.gateway.dto.equipment_service.equipments;

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
@Schema(description = "Данные для добавления информации об оборудовании")
public class NewEquipmentDto {

    @Schema(description = "Индентификатор котельной, цтп")
    @NotNull(message = "building id should not be blank")
    @Positive(message = "building id must be positive")
    private Long buildingId;
    @Schema(description = "Наименование")
    @NotBlank(message = "equipment full name should not be blank")
    private String equipmentName;
    @Schema(description = "Стационарный номер")
    private Integer stationaryNumber;
    @Schema(description = "Объем")
    private Integer volume;
    @Schema(description = "Модель")
    private String model;
    @Schema(description = "Высота")
    private Integer height;
    @Schema(description = "Длина")
    private Integer length;
    @Schema(description = "Ширина")
    private Integer width;
    @Schema(description = "Диаметр")
    private Integer diameter;
}