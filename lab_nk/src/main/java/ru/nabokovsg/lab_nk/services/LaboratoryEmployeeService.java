package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.employees.FullLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.dto.employees.ShortLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.models.LaboratoryEmployee;

import java.util.List;

public interface LaboratoryEmployeeService {

    List<ShortLaboratoryEmployeeDto> copy(Long id, String divisionType);

    FullLaboratoryEmployeeDto get(Long id);

    List<ShortLaboratoryEmployeeDto> getAll();

    void delete(Long id);

    LaboratoryEmployee getById(Long id);
    List<LaboratoryEmployee> getByAllById(List<Long> ids);
}