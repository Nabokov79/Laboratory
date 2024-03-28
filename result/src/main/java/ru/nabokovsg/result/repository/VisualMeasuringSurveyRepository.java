package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.VisualMeasuringSurvey;

public interface VisualMeasuringSurveyRepository extends JpaRepository<VisualMeasuringSurvey, Long> {

    VisualMeasuringSurvey findByEquipmentDiagnosedTaskJournalId(Long taskJournalId);
}