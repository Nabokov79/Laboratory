package ru.nabokovsg.template.dto.protocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.dto.appendices.FullAppendicesTemplateDto;
import ru.nabokovsg.template.dto.conclusion.FullConclusionTemplateDto;
import ru.nabokovsg.template.dto.header.FullHeaderTemplateDto;
import ru.nabokovsg.template.dto.subsection.FullSubsectionTemplateDto;
import ru.nabokovsg.template.dto.table.FullTableTemplateDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные шаблона протокола/заключения")
public class FullProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Заголовок")
    private FullHeaderTemplateDto leftHeaderTemplate;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String heading;
    @Schema(description = "Подразделы")
    private List<FullSubsectionTemplateDto> subsections;
    @Schema(description = "Таблицы")
    private List<FullTableTemplateDto> tables;
    @Schema(description = "Заключение по результатм")
    private FullConclusionTemplateDto conclusions;
    @Schema(description = "Приложения")
    private List<FullAppendicesTemplateDto> appendices;
}