package ru.nabokovsg.lab_nk.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.lab_nk.client.dto.ShortEmployeeDto;
import ru.nabokovsg.lab_nk.dto.employees.FullLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.dto.employees.ShortLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.models.LaboratoryEmployee;

@Mapper(componentModel = "spring")
public interface LaboratoryEmployeeMapper {

    LaboratoryEmployee mapToLaboratoryEmployee(ShortEmployeeDto employeeDto);

    ShortLaboratoryEmployeeDto mapToShortLaboratoryEmployeeDto(LaboratoryEmployee employee);

    FullLaboratoryEmployeeDto mapToFullLaboratoryEmployeeDto(LaboratoryEmployee employee);
}