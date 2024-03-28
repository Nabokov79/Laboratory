package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.result.models.DetectedRepairs;

import java.util.Set;

public interface DetectedRepairsRepository extends JpaRepository<DetectedRepairs, Long> {

    @Query("select v.defectMeasurements from VisualMeasuringSurvey v where v.equipmentDiagnosed.id =?1")
    Set<DetectedRepairs> findAllByEquipmentDiagnosedId(Long equipmentDiagnosedId);
}