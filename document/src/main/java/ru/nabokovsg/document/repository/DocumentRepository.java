package ru.nabokovsg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.document.models.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Document findByTaskJournalId(Long id);
}