package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.RepairMethod;

import java.util.Set;

public interface RepairMethodRepository extends JpaRepository<RepairMethod, Long> {

    RepairMethod findByEquipmentTypeIdAndRepairName(Long equipmentTypeId, String repairName);

    Set<RepairMethod> findAllByEquipmentTypeId(Long equipmentTypeId);
}