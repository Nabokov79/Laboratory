package ru.nabokovsg.template.dto.protocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.template.dto.header.HeaderTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения заголовка протокола/заключения")
public class ProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор типа объекта")
    private Long objectTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    private Long reportingDocumentId;
    @Schema(description = "Данные заголовка")
    private HeaderTemplateDto header;
}