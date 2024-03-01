package ru.nabokovsg.template.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.template.dto.autoData.AutoDataCollectionDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для изменения информации в подразделе")
public class SubsectionTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Тип данных подраздела")
    @NotBlank(message = "subsection name should not be blank")
    private String subsectionName;
    @Schema(description = "Порядковый номер подраздела")
    @NotNull(message = "sequential subsection number should not be null")
    @Positive(message = "sequential subsection number can only be positive")
    private double sequentialNumber;
    @Schema(description = "Текст пользователя")
    private String userText;
    @Schema(description = "Показать номер подраздела в документе")
    @NotNull(message = "sequential number visible should not be null")
    private boolean sequentialNumberVisible;
    @Schema(description = "Индентификатор сотрудника")
    private Long employeeId;
    @Schema(description = "Данные структурного подразделения")
    private DivisionDataDto division;
    @Schema(description = "Индентификаторы нормативно-технической документации")
    private List<Long> documentationIds;
    @Schema(description = "Индентификаторы средств контроля и измерений")
    private List<Long> measuringTools;
    @Schema(description = "Данные для атоматического сбора и записи информации")
    private AutoDataCollectionDto autoDataCollection;
}