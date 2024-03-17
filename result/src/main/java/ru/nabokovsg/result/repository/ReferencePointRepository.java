package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.ReferencePoint;

public interface ReferencePointRepository extends JpaRepository<ReferencePoint, Long> {
}