package ru.nabokovsg.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.template.models.DivisionData;

public interface DivisionDataRepository extends JpaRepository<DivisionData, Long> {
}