package ru.nabokovsg.company.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.contacts.ContactDto;
import ru.nabokovsg.company.dto.placeWork.PlaceWorkDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления изменения информации о сотруднике")
public class EmployeeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Отчество")
    private String patronymic;
    @Schema(description = "Фамилия")
    private String surname;
    @Schema(description = "Должность")
    private String post;
    @Schema(description = "Контакты")
    private ContactDto contact;
    @Schema(description = "Место работы")
    private PlaceWorkDto placeWork;
}