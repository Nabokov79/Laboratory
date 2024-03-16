package ru.nabokovsg.company.dto.building;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.address.ResponseAddressDto;
import ru.nabokovsg.company.dto.divisionContact.ResponseDivisionContact;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные котельной, цтп")
public class ResponseBuildingDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип строения")
    private String buildingType;
    @Schema(description = "Название")
    private String login;
    @Schema(description = "Адрес")
    private ResponseAddressDto address;
    @Schema(description = "Контакты сотрудника котельной, ЦТП")
    private ResponseDivisionContact contact;
}