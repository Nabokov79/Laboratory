package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.result.dto.repairMethod.ResponseCompletedRepairsDto;
import ru.nabokovsg.result.dto.repairMethod.CompletedRepairsDto;
import ru.nabokovsg.result.mappers.CompletedRepairsMapper;
import ru.nabokovsg.result.models.CompletedRepairs;
import ru.nabokovsg.result.repository.CompletedRepairsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompletedRepairsServiceImpl implements CompletedRepairsService {

    private final CompletedRepairsRepository repository;
    private final CompletedRepairsMapper mapper;
    private final ParametersService parameterService;


    @Override
    public ResponseCompletedRepairsDto save(CompletedRepairsDto methodDto) {
        CompletedRepairs method = repository.findByEquipmentTypeIdAndRepairName(methodDto.getEquipmentTypeId()
                                                                          , methodDto.getMethodName());
        if (method == null) {
            method = mapper.mapToRepairMethod(methodDto);
            method.setParameters(parameterService.save(methodDto.getParameters()));
            method = repository.save(method);
        }
        return mapper.mapToFullRepairMethod(method);
    }

    @Override
    public ResponseCompletedRepairsDto update(CompletedRepairsDto methodDto) {
        if (repository.existsById(methodDto.getId())) {
            CompletedRepairs method = mapper.mapToRepairMethod(methodDto);
            method.setParameters(parameterService.save(methodDto.getParameters()));
            return mapper.mapToFullRepairMethod(repository.save(method));
        }
        throw new NotFoundException(String.format("Repair method with id=%s not found for update", methodDto.getId()));
    }

    @Override
    public List<ResponseCompletedRepairsDto> getAll(Long id) {
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

    @Override
    public CompletedRepairs getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Repair method with id=%s not found", id)));
    }
}