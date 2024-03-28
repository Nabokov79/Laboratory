package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.CompletedRepairs;

import java.util.Set;

public interface CompletedRepairsRepository extends JpaRepository<CompletedRepairs, Long> {

    CompletedRepairs findByEquipmentTypeIdAndRepairName(Long equipmentTypeId, String repairName);

    Set<CompletedRepairs> findAllByEquipmentTypeId(Long equipmentTypeId);
}