package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.employee.EmployeeDto;
import ru.nabokovsg.company.dto.employee.ResponseEmployeeDto;
import ru.nabokovsg.company.dto.employee.ShortResponseEmployeeDto;
import ru.nabokovsg.company.models.DivisionContact;

import java.util.List;

public interface EmployeeService {

    ShortResponseEmployeeDto save(EmployeeDto employeeDto);

    ShortResponseEmployeeDto update(EmployeeDto employeeDto);

    ResponseEmployeeDto get(Long id);

    DivisionContact getDivisionContact(Long id);

    List<ShortResponseEmployeeDto> getAll(Long id, String divisionType);

    void delete(Long id);
}
