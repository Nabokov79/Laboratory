package ru.nabokovsg.result.dto.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения рекомендации")
public class RecommendationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор ипа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Текст рекомендации")
    private String recommendationText;
}