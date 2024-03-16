package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.employees.ResponseLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.dto.employees.ShortResponseLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.models.LaboratoryEmployee;

import java.util.List;

public interface LaboratoryEmployeeService {

    List<ShortResponseLaboratoryEmployeeDto> copy(Long id, String divisionType);

    ResponseLaboratoryEmployeeDto get(Long id);

    List<ShortResponseLaboratoryEmployeeDto> getAll();

    void delete(Long id);

    LaboratoryEmployee getById(Long id);
    List<LaboratoryEmployee> getByAllById(List<Long> ids);
}