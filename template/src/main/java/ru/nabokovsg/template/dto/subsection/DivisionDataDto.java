package ru.nabokovsg.template.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения данных структурного подразделения в подразделе документа")
public class DivisionDataDto {

    @Schema(description = "Тип структурного подразделения")
    private String divisionType;
    @Schema(description = "Индентификатор структурного подразделения организации")
    private Long divisionId;
    @Schema(description = "Пользовательское название структурного подразделения организации")
    private String userDivisionName;
    @Schema(description = "Указать адресс структурного подразделения")
    private Boolean address;
    @Schema(description = "Указать лицензию/аттестацию структурного подразделения")
    private Boolean license;
}