package ru.nabokovsg.template.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.dto.appendices.FullAppendicesTemplateDto;
import ru.nabokovsg.template.dto.pageTitle.FullPageTitleTemplateDto;
import ru.nabokovsg.template.dto.section.FullSectionTemplateDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Шаблон отчета")
public class ReportTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Титульный лист")
    private FullPageTitleTemplateDto pageTitle;
    @Schema(description = "Подразделы")
    private List<FullSectionTemplateDto> sections;
    @Schema(description = "Приложения")
    private List<FullAppendicesTemplateDto> appendices;
}