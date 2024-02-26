package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.company.models.Branch;

import java.util.Set;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Query("select o.branches from Organization o where o.id=?1")
    Set<Branch> findAllByOrganization(Long organizationId);

    Branch findByFullName(String fullName);
}