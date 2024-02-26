package ru.nabokovsg.gateway.dto.company_service.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.gateway.dto.company_service.contact.NewContactDto;
import ru.nabokovsg.gateway.dto.company_service.placeWork.NewPlaceWorkDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового сотрудника")
public class NewEmployeeDto {

    @Schema(description = "Имя")
    @NotBlank(message = "name should not be blank")
    private String name;
    @Schema(description = "Отчество")
    @NotBlank(message = "patronymic should not be blank")
    private String patronymic;
    @Schema(description = "Фамилия")
    @NotBlank(message = "surname should not be blank")
    private String surname;
    @Schema(description = "Должность")
    @NotBlank(message = "post should not be blank")
    private String post;
    @Schema(description = "Контакты")
    private NewContactDto contact;
    @Schema(description = "Место работы")
    @NotNull(message = "place work should not be null")
    private NewPlaceWorkDto placeWork;
}