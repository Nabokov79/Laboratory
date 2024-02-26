package ru.nabokovsg.lab_nk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.lab_nk.models.LaboratoryEmployee;

public interface LaboratoryEmployeeRepository extends JpaRepository<LaboratoryEmployee, Long> {
}