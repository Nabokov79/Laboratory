package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.company.models.Licenses;

import java.util.Set;

public interface LicenseRepository extends JpaRepository<Licenses, Long> {

    @Query("select o.licenses from Organization o where o.id=?1")
    Set<Licenses> findAllByOrganizationId(Long id);

    @Query("select b.licenses from Branch b where b.id=?1")
    Set<Licenses> findAllByBranchId(Long id);

    @Query("select d.licenses from Department d where d.id=?1")
    Set<Licenses> findAllByDepartmentId(Long id);
}