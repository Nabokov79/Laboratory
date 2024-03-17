package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.EquipmentDiagnosed;

import java.util.Optional;

public interface EquipmentDiagnosedRepository extends JpaRepository<EquipmentDiagnosed, Long> {

    Optional<EquipmentDiagnosed> findByTaskJournalIdAndEquipmentId(Long taskJournalId, Long equipmentId);
}