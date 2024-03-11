package ru.nabokovsg.company.dto.building;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.company.dto.address.FullAddressDto;
import ru.nabokovsg.company.dto.divisionContact.FullDivisionContact;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные котельной, цтп")
public class ShortBuildingDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип строения")
    private String buildingType;
    @Schema(description = "Название")
    private String login;
    @Schema(description = "Адрес")
    private FullAddressDto address;
    @Schema(description = "Контакты сотрудника котельной, ЦТП")
    private FullDivisionContact contact;
}