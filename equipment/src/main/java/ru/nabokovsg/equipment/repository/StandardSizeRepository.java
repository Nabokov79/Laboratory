package ru.nabokovsg.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.models.StandardSize;

public interface StandardSizeRepository extends JpaRepository<StandardSize, Long> {
}