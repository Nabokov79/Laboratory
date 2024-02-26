package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.company.models.Employee;

import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByNameAndPatronymicAndSurname(String name, String patronymic, String post);

    @Query("select e from Employee e where e.placeWork.organization.id =?1")
    Set<Employee> findAllByOrganizationId(Long id);

    @Query("select e from Employee e where e.placeWork.branch.id =?1")
    Set<Employee> findAllByBranchId(Long id);

    @Query("select e from Employee e where e.placeWork.department.id =?1")
    Set<Employee> findAllByDepartmentId(Long id);

    @Query("select e from Employee e where e.placeWork.exploitationRegion.id =?1")
    Set<Employee> findAllByExploitationRegionId(Long id);

    @Query("select e from Employee e where e.placeWork.branch.id =?1")
    Set<Employee> findAllByBuildingId(Long id);
}