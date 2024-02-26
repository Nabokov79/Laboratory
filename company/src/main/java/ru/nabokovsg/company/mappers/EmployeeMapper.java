package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.company.dto.employee.EmployeeDto;
import ru.nabokovsg.company.dto.employee.FullEmployeeDto;
import ru.nabokovsg.company.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.company.models.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee mapToEmployee(EmployeeDto employeeDto);

    FullEmployeeDto mapToFullEmployeeDto(Employee employee);

    ShortEmployeeDto mapToShortEmployeeDto(Employee employee);
}