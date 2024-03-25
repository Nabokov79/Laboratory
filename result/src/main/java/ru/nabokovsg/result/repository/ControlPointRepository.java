package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.ControlPoint;

import java.util.Set;

public interface ControlPointRepository extends JpaRepository<ControlPoint, Long> {

    Set<ControlPoint> findAllByControlPointMeasurementId(Long controlPointMeasurementId);
}