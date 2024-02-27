package ru.nabokovsg.lab_nk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.lab_nk.models.Documentation;

public interface DocumentationRepository extends JpaRepository<Documentation, Long> {

    Documentation findByTitle(String title);
}