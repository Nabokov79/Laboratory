package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.company.models.PlaceWork;

public interface PlaceWorkRepository extends JpaRepository<PlaceWork, Long> {
}