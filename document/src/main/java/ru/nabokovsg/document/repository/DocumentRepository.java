package ru.nabokovsg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.lab_nk.models.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Document findByTaskJournalId(Long id);

    @Query("select r.document from Remark r where r.id=?1")
    Document findByRemarkId(Long id);
}