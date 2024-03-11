package ru.nabokovsg.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.template.models.ProtocolReportTemplate;

import java.util.Set;

public interface ProtocolReportTemplateRepository extends JpaRepository<ProtocolReportTemplate, Long> {

    @Query("select s.protocols from SectionTemplate s where s.id = ?1")
    Set<ProtocolReportTemplate> findAllProtocols(Long id);

    ProtocolReportTemplate findByHeaderDocumentId(Long headerDocumentId);
}