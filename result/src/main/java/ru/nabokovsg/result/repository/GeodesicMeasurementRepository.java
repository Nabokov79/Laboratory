package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.Set;

public interface GeodesicMeasurementRepository extends JpaRepository<GeodesicMeasurement, Long> {

    @Query("select g.sequentialNumber from GeodesicMeasurement g where g.equipmentDiagnosed.taskJournalId =?1")
    Set<Integer> findAllSequentialNumberByTaskJournalId(Long taskJournalId);

    @Query("select g from GeodesicMeasurement g where g.equipmentDiagnosed.taskJournalId =?1")
    Set<GeodesicMeasurement> findAllByTaskJournalId(Long taskJournalId);
}