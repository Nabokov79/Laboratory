package ru.nabokovsg.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.models.Equipment;

import java.util.Set;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Set<Equipment> findAllByBuildingId(Long buildingId);
}