package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.ParameterMeasurement;

public interface ParameterMeasurementRepository extends JpaRepository<ParameterMeasurement, Long> {
}