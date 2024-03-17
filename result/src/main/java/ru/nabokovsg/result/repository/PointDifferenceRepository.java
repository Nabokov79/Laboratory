package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.PointDifference;

public interface PointDifferenceRepository extends JpaRepository<PointDifference, Long> {
}