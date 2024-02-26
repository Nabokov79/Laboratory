package ru.nabokovsg.lab_nk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.lab_nk.models.Certificate;

import java.util.Set;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    Certificate findByControlTypeAndEmployeeId(String controlType, Long employeeId);

    Set<Certificate> findAllByEmployeeId(Long employeeId);
}