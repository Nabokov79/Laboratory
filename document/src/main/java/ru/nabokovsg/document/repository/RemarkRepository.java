package ru.nabokovsg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.document.models.Remark;
import ru.nabokovsg.lab_nk.models.Remark;

import java.util.Set;

public interface RemarkRepository extends JpaRepository<Remark, Long> {

    Remark findByDocumentIdAndRemark(Long documentId, String remark);

    Set<Remark> findAllByEmployeeIdOrderByIdDesc(Long employeeId);
}