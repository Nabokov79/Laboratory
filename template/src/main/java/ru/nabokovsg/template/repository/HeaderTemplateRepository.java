package ru.nabokovsg.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.template.models.HeaderTemplate;

public interface HeaderTemplateRepository extends JpaRepository<HeaderTemplate, Long> {

    HeaderTemplate findByHeaderDocumentId(Long headerDocumentId);

    HeaderTemplate findByHeaderDocumentIdAndOrganizationId(Long headerDocumentId, Long organizationId);

    HeaderTemplate findByHeaderDocumentIdAndBranchId(Long headerDocumentId, Long branchId);

    HeaderTemplate findByHeaderDocumentIdAndDepartmentId(Long headerDocumentId, Long departmentId);

    HeaderTemplate findByHeaderDocumentIdAndHeatSupplyAreaId(Long headerDocumentId, Long heatSupplyAreaId);

    HeaderTemplate findByHeaderDocumentIdAndExploitationRegionId(Long headerDocumentId, Long exploitationRegionId);
}