package ru.nabokovsg.template.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.dto.autoData.AutoDataCollectionDto;
import ru.nabokovsg.template.dto.table.FullTableTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные подраздела")
public class FullSubsectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер подраздела")
    private double sequentialNumber;
    @Schema(description = "Название подраздела")
    private String subsectionName;
    @Schema(description = "Текст пользователя")
    private String userText;
    @Schema(description = "Показать номер подраздела в документе")
    private boolean sequentialNumberVisible;
    @Schema(description = "Данные структурного подразделения")
    private String divisionData;
    @Schema(description = "Данные об аттестации сотрудника")
    private String certificateEmployee;
    @Schema(description = "Индентификатор таблицы")
    private FullTableTemplateDto table;
    @Schema(description = "Данные для атоматического сбора и записи информации")
    private AutoDataCollectionDto autoDataCollection;
}