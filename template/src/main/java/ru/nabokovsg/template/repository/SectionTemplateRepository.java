package ru.nabokovsg.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.template.models.SectionTemplate;

import java.util.Set;

public interface SectionTemplateRepository extends JpaRepository<SectionTemplate, Long> {

    @Query("select r.sections from ReportTemplate r where r.headerDocumentId = ?1 and r.equipmentTypeId =?2")
    Set<SectionTemplate> findAllSection(Long headerDocumentId, Long equipmentTypeId);

    Set<SectionTemplate> findAllByReportIdOrderById(Long reportId);
}