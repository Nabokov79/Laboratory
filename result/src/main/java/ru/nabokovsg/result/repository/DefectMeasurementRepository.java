package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.result.models.DefectMeasurement;

import java.util.Set;

public interface DefectMeasurementRepository extends JpaRepository<DefectMeasurement, Long> {

    @Query("select v.defectMeasurements from VisualMeasuringSurvey v where v.equipmentDiagnosed.id =?1")
    Set<DefectMeasurement> findAllByEquipmentDiagnosedId(Long equipmentDiagnosedId);
}