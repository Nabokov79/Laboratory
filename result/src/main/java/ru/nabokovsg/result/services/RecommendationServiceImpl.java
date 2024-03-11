package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.result.dto.recommendation.RecommendationDto;
import ru.nabokovsg.result.dto.recommendation.FullRecommendationDto;
import ru.nabokovsg.result.mappers.RecommendationMapper;
import ru.nabokovsg.result.repository.RecommendationRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository repository;
    private final RecommendationMapper mapper;

    @Override
    public FullRecommendationDto save(RecommendationDto recommendationDto) {
        return mapper.mapToFullRecommendationDto(
                Objects.requireNonNullElseGet(
                        repository.findByEquipmentTypeIdAndRecommendationText(recommendationDto.getEquipmentTypeId()
                                                                        , recommendationDto.getRecommendationText())
                        , () -> repository.save(mapper.mapToRecommendation(recommendationDto))));
    }

    @Override
    public FullRecommendationDto update(RecommendationDto recommendationDto) {
        if (repository.existsById(recommendationDto.getId())) {
            return mapper.mapToFullRecommendationDto(repository.save(mapper.mapToRecommendation(recommendationDto)));
        }
        throw new NotFoundException(
                String.format("Recommendation with id=%s not found for update", recommendationDto.getId())
        );
    }

    @Override
    public List<FullRecommendationDto> getAll(Long id) {
        return repository.findAllByEquipmentTypeId(id).stream()
                                                             .map(mapper::mapToFullRecommendationDto)
                                                             .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Recommendation with id=%s not found for delete", id));
    }
}