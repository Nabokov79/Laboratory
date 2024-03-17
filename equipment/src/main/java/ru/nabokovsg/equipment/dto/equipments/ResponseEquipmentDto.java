package ru.nabokovsg.equipment.dto.equipments;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.equipment.dto.equipmentType.ResponseEquipmentTypeDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные оборудования")
public class ResponseEquipmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип оборудования")
    private ResponseEquipmentTypeDto equipmentType;
    @Schema(description = "Полное наименование")
    private String fullName;
    @Schema(description = "Стационарный номер")
    private Integer stationaryNumber;
    @Schema(description = "Объем")
    private Integer volume;
    @Schema(description = "Модель")
    private String model;
}