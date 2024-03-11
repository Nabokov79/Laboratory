package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.result.dto.defects.DefectDto;
import ru.nabokovsg.result.dto.defects.FullDefectDto;
import ru.nabokovsg.result.mappers.DefectMapper;
import ru.nabokovsg.result.models.Defect;
import ru.nabokovsg.result.repository.DefectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectsServiceImpl implements DefectsService {

    private final DefectRepository repository;
    private final DefectMapper mapper;
    private final SizeParametersService parameterService;


    @Override
    public FullDefectDto save(DefectDto defectDto) {
        Defect defect = repository.findByEquipmentTypeIdAndDefectName(defectDto.getEquipmentTypeId()
                                                                    , defectDto.getDefectName());
        if (defect == null) {
            defect = mapper.mapToDefect(defectDto);
            defect.setSizeParameters(parameterService.save(defectDto.getParameters()));
            defect = repository.save(defect);
        }
        return mapper.mapToFullDefectDto(defect);
    }

    @Override
    public FullDefectDto update(DefectDto defectDto) {
        if (repository.existsById(defectDto.getId())) {
            Defect defect = mapper.mapToDefect(defectDto);
            defect.setSizeParameters(parameterService.update(defectDto.getParameters()));
            return mapper.mapToFullDefectDto(repository.save(defect));
        }
        throw new NotFoundException(String.format("Defect with id=%s not found for update", defectDto.getId()));
    }

    @Override
    public List<FullDefectDto> getAll(Long id) {
        return repository.findAllByEquipmentTypeId(id)
                         .stream()
                         .map(mapper::mapToFullDefectDto)
                         .toList();
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