package ru.nabokovsg.company.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.certificate.ResponseCertificateDto;
import ru.nabokovsg.company.dto.contacts.ResponseContactDto;
import ru.nabokovsg.company.dto.placeWork.ResponsePlaceWorkDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Полные информация о сотруднике")
public class ResponseEmployeeDto {

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
    @Schema(description = "Место работы сотрудника")
    private ResponsePlaceWorkDto placeWork;
    @Schema(description = "Контакты сотрудника")
    private ResponseContactDto contact;
    @Schema(description = "Список сертификатов сотрудника")
    private List<ResponseCertificateDto> certificate;
}