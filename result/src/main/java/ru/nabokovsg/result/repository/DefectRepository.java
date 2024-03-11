package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.Defect;

import java.util.Set;

public interface DefectRepository extends JpaRepository<Defect, Long> {

    Defect findByEquipmentTypeIdAndDefectName(Long equipmentTypeId, String defectName);

    Set<Defect> findAllByEquipmentTypeId(Long equipmentTypeId);
}