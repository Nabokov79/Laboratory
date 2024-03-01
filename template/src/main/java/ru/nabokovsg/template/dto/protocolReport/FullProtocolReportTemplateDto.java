package ru.nabokovsg.template.dto.protocolReport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.dto.conclusion.FullConclusionTemplateDto;
import ru.nabokovsg.template.dto.subsection.FullSubsectionTemplateDto;
import ru.nabokovsg.template.dto.table.FullTableTemplateDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные шаблона протокола отчета")
public class FullProtocolReportTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Заголовок с порядковым номером протокола")
    private String title;
    @Schema(description = "Название протокола")
    private String heading;
    @Schema(description = "Текст пользователя после заголовка")
    private String userTextAfterHeading;
    @Schema(description = "Шаблоны подразделов")
    private List<FullSubsectionTemplateDto> subsections;
    @Schema(description = "Шаблон таблиц")
    private List<FullTableTemplateDto> tables;
    @Schema(description = "Шаблон заключений")
    private FullConclusionTemplateDto conclusions;
}