package ru.nabokovsg.lab_nk.dto.remark;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные замечания к документу или чертежу")
public class FullRemarkDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор документа")
    private Long documentId;
    @Schema(description = "Замечание к документу или чертежу")
    private String remark;
    @Schema(description = "Фамилия, инициалы сотрудника")
    private Long employee;
    @Schema(description = "Отметка об исправлении замечания")
    private Boolean documentCorrected;
}
