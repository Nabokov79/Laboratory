package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.HardnessMeasurement;

import java.util.Set;

public interface HardnessMeasurementRepository extends JpaRepository<HardnessMeasurement, Long> {

    Set<HardnessMeasurement> findAllByEquipmentDiagnosedTaskJournalIdAndEquipmentDiagnosedEquipmentId(Long taskJournalId
                                                                                                    , Long equipmentId);

    Set<HardnessMeasurement> findAllByEquipmentDiagnosedTaskJournalId(Long taskJournalId);
}