package ru.nabokovsg.template.dto.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.template.dto.header.FullHeaderTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Краткие данныее титульного листа отчета")
public class ShortPageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Заголовок")
    private FullHeaderTemplateDto header;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String heading;
    @Schema(description = "Строка наименования объекта")
    private String equipment;
}