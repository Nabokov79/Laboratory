package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.AcceptableThickness;

import java.util.Set;

public interface AcceptableThicknessRepository extends JpaRepository<AcceptableThickness, Long> {

    Set<AcceptableThickness> findAllByEquipmentTypeId(Long equipmentTypeId);
}