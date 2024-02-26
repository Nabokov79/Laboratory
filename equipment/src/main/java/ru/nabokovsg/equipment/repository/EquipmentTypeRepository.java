package ru.nabokovsg.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.models.EquipmentType;

public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {
}