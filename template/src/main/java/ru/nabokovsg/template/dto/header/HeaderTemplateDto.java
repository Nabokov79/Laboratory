package ru.nabokovsg.template.dto.header;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные добавления/изменения заголовка протокола, заключения, загоовка титульного листа отчета")
public class HeaderTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор типа документа")
    private Long headerDocumentId;
    @Schema(description = "Тип структурного подразделения организации")
    private String divisionType;
    @Schema(description = "Индентификатор структурного подразделения организации")
    private Long divisionId;
    @Schema(description = "Указать в документе полное наименование")
    private Boolean specifyFullName;
    @Schema(description = "Указать адрес")
    private Boolean specifyAddress;
    @Schema(description = "Указать лицензию/аттестацию")
    private Boolean specifyLicense;
    @Schema(description = "Указать контактные данные организации")
    private Boolean specifyContacts;
}