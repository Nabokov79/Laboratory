package ru.nabokovsg.lab_nk.dto.headerDocument;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.lab_nk.models.enums.TypeDocument;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные вида документа")
public class FullHeaderDocumentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String heading;
    @Schema(description = "Тип документа")
    private TypeDocument typeDocument;
}