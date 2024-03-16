package ru.nabokovsg.template.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.dto.appendices.ResponseAppendicesTemplateDto;
import ru.nabokovsg.template.dto.pageTitle.ResponsePageTitleTemplateDto;
import ru.nabokovsg.template.dto.section.ResponseSectionTemplateDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Шаблон отчета")
public class ResponseReportTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Титульный лист")
    private ResponsePageTitleTemplateDto pageTitle;
    @Schema(description = "Подразделы")
    private List<ResponseSectionTemplateDto> sections;
    @Schema(description = "Приложения")
    private List<ResponseAppendicesTemplateDto> appendices;
}