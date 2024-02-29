package ru.nabokovsg.lab_nk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.lab_nk.models.Remark;

import java.util.Set;

public interface RemarkRepository extends JpaRepository<Remark, Long> {

    Remark findByDocumentIdAndEmployeeIdAndRemark(Long documentId, Long employeeId, String remark);

    Set<Remark> findAllByEmployeeIdOrderByIdDesc(Long employeeId);
}