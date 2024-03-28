package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.result.dto.defects.DefectDto;
import ru.nabokovsg.result.dto.defects.ResponseDefectDto;
import ru.nabokovsg.result.mappers.DefectMapper;
import ru.nabokovsg.result.models.Defect;
import ru.nabokovsg.result.repository.DefectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectsServiceImpl implements DefectsService {

    private final DefectRepository repository;
    private final DefectMapper mapper;
    private final ParametersService parameterService;


    @Override
    public ResponseDefectDto save(DefectDto defectDto) {
        Defect defect = repository.findByEquipmentTypeIdAndDefectName(defectDto.getEquipmentTypeId()
                                                                    , defectDto.getDefectName());
        if (defect == null) {
            defect = repository.save(mapper.mapToDefect(defectDto, parameterService.save(defectDto.getParameters())));
        }
        return mapper.mapToFullDefectDto(defect);
    }

    @Override
    public ResponseDefectDto update(DefectDto defectDto) {
        if (repository.existsById(defectDto.getId())) {
            return mapper.mapToFullDefectDto(
                    repository.save(mapper.mapToDefect(defectDto, parameterService.update(defectDto.getParameters())))
            );
        }
        throw new NotFoundException(String.format("Defect with id=%s not found for update", defectDto.getId()));
    }

    @Override
    public List<ResponseDefectDto> getAll(Long id) {
        return repository.findAllByEquipmentTypeId(id)
                         .stream()
                         .map(mapper::mapToFullDefectDto)
                         .toList();
    }

    @Override
    public Defect get(Long id) {
        return repository.findById(id)
                           .orElseThrow(() ->  new NotFoundException(String.format("Defect with id=%s not found", id)));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Defect with id=%s not found for delete", id));
    }
}