package ru.nabokovsg.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.template.models.PageTitleTemplate;

public interface PageTitleTemplateRepository extends JpaRepository<PageTitleTemplate, Long> {
}