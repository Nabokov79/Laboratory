package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.company.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}