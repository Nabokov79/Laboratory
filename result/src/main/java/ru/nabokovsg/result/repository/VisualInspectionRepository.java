package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.VisualInspection;

public interface VisualInspectionRepository extends JpaRepository<VisualInspection, Long> {
}