package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.company.dto.department.DepartmentDto;
import ru.nabokovsg.company.dto.department.FullDepartmentDto;
import ru.nabokovsg.company.dto.department.ShortDepartmentDto;
import ru.nabokovsg.company.models.Address;
import ru.nabokovsg.company.models.Branch;
import ru.nabokovsg.company.models.Department;
import ru.nabokovsg.company.models.DivisionContact;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(source = "departmentDto.fullName", target = "fullName")
    @Mapping(source = "departmentDto.shortName", target = "shortName")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "divisionContact", target = "contact")
    @Mapping(source = "branch", target = "branch")
    @Mapping(source = "departmentDto.id", target = "id")
    Department mapToDepartment(DepartmentDto departmentDto, DivisionContact divisionContact
                                , Address address, Branch branch);

    FullDepartmentDto mapToFullDepartmentDto(Department department);

    ShortDepartmentDto mapToShortDepartmentDto(Department department);
}