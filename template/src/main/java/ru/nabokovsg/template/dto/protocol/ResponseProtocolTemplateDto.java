package ru.nabokovsg.template.dto.protocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.dto.appendices.ResponseAppendicesTemplateDto;
import ru.nabokovsg.template.dto.conclusion.ResponseConclusionTemplateDto;
import ru.nabokovsg.template.dto.header.ResponseHeaderTemplateDto;
import ru.nabokovsg.template.dto.subsection.ResponseSubsectionTemplateDto;
import ru.nabokovsg.template.dto.table.ResponseTableTemplateDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные шаблона протокола/заключения")
public class ResponseProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Заголовок")
    private ResponseHeaderTemplateDto leftHeaderTemplate;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String heading;
    @Schema(description = "Подразделы")
    private List<ResponseSubsectionTemplateDto> subsections;
    @Schema(description = "Таблицы")
    private List<ResponseTableTemplateDto> tables;
    @Schema(description = "Заключение по результатм")
    private ResponseConclusionTemplateDto conclusions;
    @Schema(description = "Приложения")
    private List<ResponseAppendicesTemplateDto> appendices;
}