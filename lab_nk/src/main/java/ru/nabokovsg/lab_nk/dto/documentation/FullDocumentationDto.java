package ru.nabokovsg.lab_nk.dto.documentation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нормативно-технической документации")
public class FullDocumentationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Вид документа")
    private String view;
    @Schema(description = "Номер документа")
    private String number;
    @Schema(description = "Заголовок документа")
    private String title;
    @Schema(description = "Путь к файлу в файловом хранилище")
    private String path;
}