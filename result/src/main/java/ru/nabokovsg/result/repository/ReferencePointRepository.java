package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.ReferencePoint;

import java.util.Set;

public interface ReferencePointRepository extends JpaRepository<ReferencePoint, Long> {

    Set<ReferencePoint> getAllByEquipmentDiagnosedId(Long equipmentDiagnosedId);
}