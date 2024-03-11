package ru.nabokovsg.template.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Структурное подразделение предприятия")
public class DivisionDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String fullName;
    @Schema(description = "Краткое название")
    private String shortName;
    @Schema(description = "Адрес")
    private AddressDto address;
    @Schema(description = "Контакты структурного подразделения предприятия")
    private DivisionContact contact;
    @Schema(description = "Лицензия/аттестация сруктурного подразделения")
    private List<LicenseDto> licenses;
}