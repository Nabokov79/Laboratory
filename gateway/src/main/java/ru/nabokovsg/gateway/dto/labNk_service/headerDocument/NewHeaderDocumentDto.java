package ru.nabokovsg.gateway.dto.labNk_service.headerDocument;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления вида документа")
public class NewHeaderDocumentDto {

    @Schema(description = "Название документа")
    @NotBlank(message = "title should not be blank")
    private String title;
    @Schema(description = "Заголовок документа")
    @NotBlank(message = "heading should not be blank")
    private String heading;
    @Schema(description = "Тип документа")
    @NotBlank(message = "document type should not be blank")
    private String typeDocument;
}