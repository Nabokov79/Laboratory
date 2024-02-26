package ru.nabokovsg.lab_nk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.lab_nk.models.HeaderDocument;
import ru.nabokovsg.lab_nk.models.enums.TypeDocument;

public interface HeaderDocumentRepository extends JpaRepository<HeaderDocument, Long> {

    HeaderDocument findByTitleAndHeadingAndTypeDocument(String title, String heading, TypeDocument typeDocument);
}