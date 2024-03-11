package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.Recommendation;

import java.util.Set;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    Recommendation findByEquipmentTypeIdAndRecommendationText(Long equipmentTypeId, String recommendationText);

    Set<Recommendation> findAllByEquipmentTypeId(Long equipmentTypeId);
}