package ru.nabokovsg.company.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.employee.EmployeeDto;
import ru.nabokovsg.company.dto.employee.FullEmployeeDto;
import ru.nabokovsg.company.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.company.exceptions.BadRequestException;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mappers.EmployeeMapper;
import ru.nabokovsg.company.models.Employee;
import ru.nabokovsg.company.models.enums.DivisionType;
import ru.nabokovsg.company.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final ContactService contactService;
    private final PlaceWorkService placeWorkService;

    @Override
    public ShortEmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = repository.findByNameAndPatronymicAndSurname(employeeDto.getName()
                                                                       , employeeDto.getPatronymic()
                                                                       , employeeDto.getSurname());
        if (employee == null) {
            employee = mapper.mapToEmployee(employeeDto);
            employee.setPlaceWork(placeWorkService.save(employeeDto.getPlaceWork()));
            if (employeeDto.getContact() != null) {
                employee.setContact(contactService.save(employeeDto.getContact()));
            }
            employee = repository.save(employee);
        }
        return mapper.mapToShortEmployeeDto(employee);
    }

    @Override
    public ShortEmployeeDto update(EmployeeDto employeeDto) {
        if (repository.existsById(employeeDto.getId())) {
            Employee employee = mapper.mapToEmployee(employeeDto);
            employee.setPlaceWork(placeWorkService.update(employeeDto.getPlaceWork()));
            if (employeeDto.getContact() != null) {
                employee.setContact(contactService.update(employeeDto.getContact()));
            }
            return mapper.mapToShortEmployeeDto(repository.save(employee));
            }
        throw new NotFoundException(String.format("Employee with id=%s not found for update",employeeDto.getId()));
    }

    @Override
    public FullEmployeeDto get(Long id) {
        return mapper.mapToFullEmployeeDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with id=%s was not found", id))));
    }

    @Override
    public List<ShortEmployeeDto> getAll(Long id, String divisionType) {
        Set<Employee> employees = null;
        switch (convert(divisionType)) {
            case ORGANIZATION -> employees = repository.findAllByOrganizationId(id);
            case BRANCH -> employees = repository.findAllByBranchId(id);
            case DEPARTMENT -> employees = repository.findAllByDepartmentId(id);
            case EXPLOITATION_REGION -> employees = repository.findAllByExploitationRegionId(id);
            case BUILDING -> employees = repository.findAllByBuildingId(id);
        }
        if (employees == null) {
            return new ArrayList<>();
        }
        return employees.stream()
                        .map(mapper::mapToShortEmployeeDto)
                        .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Employee with id = %s not found for delete",id));
    }

    private DivisionType convert(String divisionType) {
        return DivisionType.from(divisionType)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Unknown data format divisionType=%s", divisionType))
                );
    }
}