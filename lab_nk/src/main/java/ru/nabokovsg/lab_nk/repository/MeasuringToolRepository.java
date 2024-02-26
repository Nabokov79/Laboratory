package ru.nabokovsg.lab_nk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.lab_nk.models.MeasuringTool;

public interface MeasuringToolRepository extends JpaRepository<MeasuringTool, Long> {
}