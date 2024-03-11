package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.recommendation.FullRecommendationDto;
import ru.nabokovsg.result.dto.recommendation.RecommendationDto;

import java.util.List;

public interface RecommendationService {

    FullRecommendationDto save(RecommendationDto recommendationDto);

    FullRecommendationDto update(RecommendationDto recommendationDto);

    List<FullRecommendationDto> getAll(Long id);

    void delete(Long id);
}