package ru.nabokovsg.template.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.models.enums.TypeDocument;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные вида документа")
public class HeaderDocumentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String heading;
    @Schema(description = "Тип документа")
    private TypeDocument typeDocument;
}