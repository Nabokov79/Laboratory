package ru.nabokovsg.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.models.EquipmentPassport;

public interface EquipmentPassportRepository extends JpaRepository<EquipmentPassport, Long> {

    EquipmentPassport findByHeaderAndEquipmentId(String header, Long equipmentId);
}