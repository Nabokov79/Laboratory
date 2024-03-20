package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.EquipmentDiagnosed;

public interface EquipmentDiagnosedRepository extends JpaRepository<EquipmentDiagnosed, Long> {

   EquipmentDiagnosed findByTaskJournalIdAndEquipmentId(Long taskJournalId, Long equipmentId);
}