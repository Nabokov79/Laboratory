package ru.nabokovsg.template.dto.appendices;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные приложения к документу")
public class FullAppendicesTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер")
    private Integer sequentialNumber;
    @Schema(description = "Наименование")
    private String appendicesName;
}