package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.ReferencePoint;

public interface CalculatingGeodesicMeasurementRepository extends JpaRepository<ReferencePoint, Long> {
}