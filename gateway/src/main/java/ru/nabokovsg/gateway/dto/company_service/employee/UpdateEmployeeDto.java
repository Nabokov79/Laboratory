package ru.nabokovsg.gateway.dto.company_service.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.gateway.dto.company_service.contact.UpdateContactDto;
import ru.nabokovsg.gateway.dto.company_service.placeWork.UpdatePlaceWorkDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о сотруднике")
public class UpdateEmployeeDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id user should not be blank")
    @Positive(message = "id user must be positive")
    private Long id;
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
    @Schema(description = "Контакты сотрудника")
    private UpdateContactDto contact;
    @Schema(description = "Место работы")
    @NotNull(message = "place work should not be null")
    private UpdatePlaceWorkDto placeWork;
}