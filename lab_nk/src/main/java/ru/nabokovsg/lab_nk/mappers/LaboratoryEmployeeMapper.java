package ru.nabokovsg.lab_nk.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.lab_nk.client.dto.ShortEmployeeDto;
import ru.nabokovsg.lab_nk.dto.employees.ResponseLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.dto.employees.ShortResponseLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.models.LaboratoryEmployee;

@Mapper(componentModel = "spring")
public interface LaboratoryEmployeeMapper {

    LaboratoryEmployee mapToLaboratoryEmployee(ShortEmployeeDto employeeDto);

    ShortResponseLaboratoryEmployeeDto mapToShortLaboratoryEmployeeDto(LaboratoryEmployee employee);

    ResponseLaboratoryEmployeeDto mapToFullLaboratoryEmployeeDto(LaboratoryEmployee employee);
}