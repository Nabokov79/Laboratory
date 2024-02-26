package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.employee.EmployeeDto;
import ru.nabokovsg.company.dto.employee.FullEmployeeDto;
import ru.nabokovsg.company.dto.employee.ShortEmployeeDto;

import java.util.List;

public interface EmployeeService {

    ShortEmployeeDto save(EmployeeDto employeeDto);

    ShortEmployeeDto update(EmployeeDto employeeDto);

    FullEmployeeDto get(Long id);

    List<ShortEmployeeDto> getAll(Long id, String divisionType);

    void delete(Long id);
}
