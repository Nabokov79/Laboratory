package ru.nabokovsg.result.dto.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные рекомендации")
public class FullRecommendationDto {

    @Schema(description = "Индентификатор")
    Long id;
    @Schema(description = "Текст рекомендации")
    String recommendationText;
}