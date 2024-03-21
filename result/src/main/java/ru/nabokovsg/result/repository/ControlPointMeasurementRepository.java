package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.ControlPointMeasurement;

public interface ControlPointMeasurementRepository extends JpaRepository<ControlPointMeasurement, Long> {

    ControlPointMeasurement findByEquipmentDiagnosedId(Long equipmentDiagnosedId);
}