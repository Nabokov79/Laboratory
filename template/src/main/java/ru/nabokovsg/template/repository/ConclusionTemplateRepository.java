package ru.nabokovsg.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.template.models.ConclusionTemplate;

public interface ConclusionTemplateRepository extends JpaRepository<ConclusionTemplate, Long> {

    @Query("select p.conclusions from ProtocolTemplate p where p.headerDocumentId = ?1")
    ConclusionTemplate findByProtocolId(Long headerDocumentId);

    @Query("select p.conclusions from ProtocolReportTemplate p where p.headerDocumentId= ?1")
    ConclusionTemplate findByProtocolReportId(Long headerDocumentId);
}