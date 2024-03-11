package ru.nabokovsg.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.template.models.DocumentationTemplate;

public interface DocumentationTemplateRepository extends JpaRepository<DocumentationTemplate, Long> {
}