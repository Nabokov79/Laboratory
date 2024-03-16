package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.department.DepartmentDto;
import ru.nabokovsg.company.dto.department.ResponseDepartmentDto;
import ru.nabokovsg.company.dto.department.ShortResponseDepartmentDto;
import ru.nabokovsg.company.models.Department;
import ru.nabokovsg.company.models.Licenses;
import java.util.List;

public interface DepartmentService {

    ShortResponseDepartmentDto save(DepartmentDto departmentDto);

    ShortResponseDepartmentDto update(DepartmentDto departmentDto);

    ResponseDepartmentDto get(Long id);

    Department getById(Long id);

    List<ShortResponseDepartmentDto> getAll(Long id);

    void addLicense(Long id, Licenses license);

    void delete(Long id);
}