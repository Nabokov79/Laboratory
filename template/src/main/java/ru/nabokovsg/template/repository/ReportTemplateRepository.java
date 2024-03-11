package ru.nabokovsg.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.template.models.ReportTemplate;

public interface ReportTemplateRepository extends JpaRepository<ReportTemplate, Long> {

    ReportTemplate findByHeaderDocumentIdAndEquipmentTypeId(Long headerDocumentId, Long equipmentTypeId);

    ReportTemplate findByEquipmentTypeId(Long equipmentTypeId);
}