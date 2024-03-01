package ru.nabokovsg.template.dto.conclusion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Тексты заключений к протоколам.")
public class ConclusionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Основной текст заключения")
    private String headerText;
    @Schema(description = "Текст заключения при отсутствии дефектов")
    private String ifThanNorm;
    @Schema(description = "Текст заключения при приближении значения к допустимому")
    private String approaching;
    @Schema(description = "Текст заключения при равенстве замера допустимому")
    private String equal;
    @Schema(description = "Текст при отсутствии норм браковки")
    private String ifNoNorm;
    @Schema(description = "Текст закличения при наличии брака")
    private String ifLessNorm;
}
