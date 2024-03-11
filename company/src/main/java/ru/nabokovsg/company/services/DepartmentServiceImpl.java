package ru.nabokovsg.company.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.department.DepartmentDto;
import ru.nabokovsg.company.dto.department.ShortDepartmentDto;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.models.Department;
import ru.nabokovsg.company.models.Licenses;
import ru.nabokovsg.company.mappers.DepartmentMapper;
import ru.nabokovsg.company.repository.DepartmentRepository;
import ru.nabokovsg.company.dto.department.FullDepartmentDto;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;
    private final AddressService addressService;
    private final BranchService branchService;
    private final EmployeeService employeeService;

    @Override
    public ShortDepartmentDto save(DepartmentDto departmentDto) {
        return mapper.mapToShortDepartmentDto(
                Objects.requireNonNullElseGet(repository.findByFullName(departmentDto.getFullName())
                        , () -> repository.save(
                                mapper.mapToDepartment(departmentDto
                                                     , employeeService.getDivisionContact(departmentDto.getEmployeeId())
                                                     , addressService.get(departmentDto.getAddressId())
                                                     , branchService.getById(departmentDto.getBranchId())))));
    }

    @Override
    public ShortDepartmentDto update(DepartmentDto departmentDto) {
        if (repository.existsById(departmentDto.getId())) {
            return mapper.mapToShortDepartmentDto(
                    repository.save(
                            mapper.mapToDepartment(departmentDto
                                                 , employeeService.getDivisionContact(departmentDto.getEmployeeId())
                                                 , addressService.get(departmentDto.getAddressId())
                                                 , branchService.getById(departmentDto.getBranchId())))
            );
        }
        throw new NotFoundException(
                String.format("Department with name=%s not found for update.", departmentDto.getFullName()));
    }

    @Override
    public FullDepartmentDto get(Long id) {
        return mapper.mapToFullDepartmentDto(getById(id));
    }

    @Override
    public Department getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Department with id=%s not found", id)));
    }

    @Override
    public List<ShortDepartmentDto> getAll(Long branchId) {
        return repository.findByBranch(branchId)
                         .stream()
                         .map(mapper::mapToShortDepartmentDto)
                         .toList();
    }

    @Override
    public void addLicense(Long id, Licenses license) {
        Department department = getById(id);
        department.getLicenses().add(license);
        repository.save(department);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Department with id=%s not found for delete.", id));
    }
}