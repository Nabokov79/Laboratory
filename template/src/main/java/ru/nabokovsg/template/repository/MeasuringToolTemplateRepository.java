package ru.nabokovsg.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.template.models.MeasuringToolTemplate;

public interface MeasuringToolTemplateRepository extends JpaRepository<MeasuringToolTemplate, Long> {
}