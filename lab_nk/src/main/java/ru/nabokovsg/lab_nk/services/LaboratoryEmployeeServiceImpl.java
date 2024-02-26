package ru.nabokovsg.lab_nk.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.client.LadNKClient;
import ru.nabokovsg.lab_nk.dto.employees.DivisionDataDto;
import ru.nabokovsg.lab_nk.dto.employees.FullLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.dto.employees.ShortLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.exceptions.NotFoundException;
import ru.nabokovsg.lab_nk.mappers.LaboratoryEmployeeMapper;
import ru.nabokovsg.lab_nk.models.LaboratoryEmployee;
import ru.nabokovsg.lab_nk.repository.LaboratoryEmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LaboratoryEmployeeServiceImpl implements LaboratoryEmployeeService {

    private final LaboratoryEmployeeRepository repository;
    private final LaboratoryEmployeeMapper mapper;
    private final LadNKClient client;

    @Override
    public List<ShortLaboratoryEmployeeDto> save(DivisionDataDto divisionDataDto) {
        List<LaboratoryEmployee> laboratoryEmployees = client.getAllEmployee(divisionDataDto)
                                                             .stream()
                                                             .map(mapper::mapToLaboratoryEmployee)
                                                             .toList();
        try {
            laboratoryEmployees = repository.saveAll(laboratoryEmployees);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        return laboratoryEmployees.stream()
                                  .map(mapper::mapToShortLaboratoryEmployeeDto)
                                  .toList();
    }

    @Override
    public FullLaboratoryEmployeeDto get(Long id) {
        return mapper.mapToFullLaboratoryEmployeeDto(getById(id));
    }

    @Override
    public List<ShortLaboratoryEmployeeDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToShortLaboratoryEmployeeDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("LaboratoryEmployee with id=%s not found for delete", id));
    }

    @Override
    public LaboratoryEmployee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("LaboratoryEmployee with id=%s not found", id)));
    }
}