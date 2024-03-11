package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.result.dto.repairMethod.FullRepairMethodDto;
import ru.nabokovsg.result.dto.repairMethod.RepairMethodDto;
import ru.nabokovsg.result.mappers.RepairMethodMapper;
import ru.nabokovsg.result.models.RepairMethod;
import ru.nabokovsg.result.repository.RepairMethodRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairMethodServiceImpl implements RepairMethodService {

    private final RepairMethodRepository repository;
    private final RepairMethodMapper mapper;
    private final SizeParametersService parameterService;


    @Override
    public FullRepairMethodDto save(RepairMethodDto methodDto) {
        RepairMethod method = repository.findByEquipmentTypeIdAndRepairName(methodDto.getEquipmentTypeId()
                                                                          , methodDto.getMethodName());
        if (method == null) {
            method = mapper.mapToRepairMethod(methodDto);
            method.setSizeParameters(parameterService.save(methodDto.getParameters()));
            method = repository.save(method);
        }
        return mapper.mapToFullRepairMethod(method);
    }

    @Override
    public FullRepairMethodDto update(RepairMethodDto methodDto) {
        if (repository.existsById(methodDto.getId())) {
            RepairMethod method = mapper.mapToRepairMethod(methodDto);
            method.setSizeParameters(parameterService.save(methodDto.getParameters()));
            return mapper.mapToFullRepairMethod(repository.save(method));
        }
        throw new NotFoundException(String.format("Repair method with id=%s not found for update", methodDto.getId()));
    }

    @Override
    public List<FullRepairMethodDto> getAll(Long id) {
        return repository.findAllByEquipmentTypeId(id)
                         .stream()
                         .map(mapper::mapToFullRepairMethod)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Repair method with id=%s not found for delete", id));
    }
}