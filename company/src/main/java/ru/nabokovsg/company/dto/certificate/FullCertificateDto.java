package ru.nabokovsg.company.dto.certificate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.organization.ShortOrganizationDto;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные аттестации сотрудника")
public class FullCertificateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Тип документа")
    private String documentType;
    @Schema(description = "Номер сертификата")
    private String certificateNumber;
    @Schema(description = "Вид контроля соглано сертификата")
    private String controlType;
    @Schema(description = "Квалификационный уровень сотрудника по данным из сертификата")
    private String level;
    @Schema(description = "Дата выдачи сертификата специализированной организацией")
    private LocalDate startDate;
    @Schema(description = "Дата окончания действия сертификата")
    private LocalDate endDate;
    @Schema(description = "Шифр объектов, для контроля которых допущен сотрудник согласно данным сертификата")
    private String points;
    @Schema(description = "Организация, выдавшая сертификат")
    private ShortOrganizationDto organization;
}