package ru.nabokovsg.company.dto.licenses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения лицензии/свитедельства")
public class LicenseDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Вид документа")
    private String documentType;
    @Schema(description = "Номер")
    private String licenseNumber;
    @Schema(description = "Дата выдачи")
    private LocalDate startData;
    @Schema(description = "Дата окончания действия")
    private LocalDate endData;
    @Schema(description = "Индентификатор организации выдавшей документ")
    private Long issuedLicenseId;
    @Schema(description = "Тип структурного подразделения")
    private String divisionType;
    @Schema(description = "Индентификатор структурного подразделения организации")
    private Long divisionId;
}