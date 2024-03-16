package ru.nabokovsg.template.dto.protocolReport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.dto.conclusion.ResponseConclusionTemplateDto;
import ru.nabokovsg.template.dto.subsection.ResponseSubsectionTemplateDto;
import ru.nabokovsg.template.dto.table.ResponseTableTemplateDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные шаблона протокола отчета")
public class ResponseProtocolReportTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Заголовок с порядковым номером протокола")
    private String title;
    @Schema(description = "Название протокола")
    private String heading;
    @Schema(description = "Текст пользователя после заголовка")
    private String userTextAfterHeading;
    @Schema(description = "Шаблоны подразделов")
    private List<ResponseSubsectionTemplateDto> subsections;
    @Schema(description = "Шаблон таблиц")
    private List<ResponseTableTemplateDto> tables;
    @Schema(description = "Шаблон заключений")
    private ResponseConclusionTemplateDto conclusions;
}