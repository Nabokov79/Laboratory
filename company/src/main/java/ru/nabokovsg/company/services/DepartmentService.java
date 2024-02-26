package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.department.DepartmentDto;
import ru.nabokovsg.company.dto.department.FullDepartmentDto;
import ru.nabokovsg.company.dto.department.ShortDepartmentDto;
import ru.nabokovsg.company.models.Department;
import ru.nabokovsg.company.models.Licenses;
import java.util.List;

public interface DepartmentService {

    ShortDepartmentDto save(DepartmentDto departmentDto);

    ShortDepartmentDto update(DepartmentDto departmentDto);

    FullDepartmentDto get(Long id);

    Department getById(Long id);

    List<ShortDepartmentDto> getAll(Long id);

    void addLicense(Long id, Licenses license);

    void delete(Long id);
}