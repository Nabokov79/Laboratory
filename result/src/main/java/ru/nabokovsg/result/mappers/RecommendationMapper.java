package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.result.dto.recommendation.FullRecommendationDto;
import ru.nabokovsg.result.dto.recommendation.RecommendationDto;
import ru.nabokovsg.result.models.Recommendation;

@Mapper(componentModel = "spring")
public interface RecommendationMapper {

    Recommendation mapToRecommendation(RecommendationDto recommendationDto);

    FullRecommendationDto mapToFullRecommendationDto(Recommendation recommendation);
}