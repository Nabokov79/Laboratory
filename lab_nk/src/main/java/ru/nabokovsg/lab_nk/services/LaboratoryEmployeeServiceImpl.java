package ru.nabokovsg.lab_nk.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.client.LadNKClient;
import ru.nabokovsg.lab_nk.dto.employees.ResponseLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.dto.employees.ShortResponseLaboratoryEmployeeDto;
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
    public List<ShortResponseLaboratoryEmployeeDto> copy(Long id, String divisionType) {
        List<LaboratoryEmployee> laboratoryEmployees = client.getAllEmployee(id, divisionType)
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
    public ResponseLaboratoryEmployeeDto get(Long id) {
        return mapper.mapToFullLaboratoryEmployeeDto(getById(id));
    }

    @Override
    public List<ShortResponseLaboratoryEmployeeDto> getAll() {
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

    @Override
    public List<LaboratoryEmployee> getByAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }
}