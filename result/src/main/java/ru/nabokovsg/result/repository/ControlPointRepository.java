package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.ControlPoint;

public interface ControlPointRepository extends JpaRepository<ControlPoint, Long> {
}
