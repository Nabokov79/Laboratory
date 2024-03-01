package ru.nabokovsg.template.dto.section;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.template.dto.protocolReport.FullProtocolReportTemplateDto;
import ru.nabokovsg.template.dto.subsection.FullSubsectionTemplateDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Полные данные раздела документа")
public class FullSectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер")
    private Integer sequentialNumber;
    @Schema(description = "Название")
    private String sectionName;
    @Schema(description = "Подразделы")
    private List<FullSubsectionTemplateDto> subsections;
    @Schema(description = "Протоколы")
    private List<FullProtocolReportTemplateDto> protocols;
}