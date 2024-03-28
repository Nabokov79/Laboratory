package ru.nabokovsg.result.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.defectRepair.DefectRepairDto;
import ru.nabokovsg.result.dto.defectRepair.ResponseDefectRepairDto;
import ru.nabokovsg.result.exceptions.NotFoundException;
import ru.nabokovsg.result.mappers.DetectedRepairsMapper;
import ru.nabokovsg.result.models.DetectedRepairs;
import ru.nabokovsg.result.models.QDetectedRepairs;
import ru.nabokovsg.result.models.builders.ParameterMeasurementBuilder;
import ru.nabokovsg.result.repository.DetectedRepairsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetectedRepairsServiceImpl implements DetectedRepairsService {

    private final DetectedRepairsRepository repository;
    private final DetectedRepairsMapper mapper;
    private final VisualMeasuringSurveyService vmsService;
    private final CompletedRepairsService completedRepairsService;
    private final ParameterMeasurementService parameterMeasurementService;
    private final EntityManager em;

    @Override
    public ResponseDefectRepairDto save(DefectRepairDto defectRepairDto) {
        DetectedRepairs repair = getByPredicate(defectRepairDto);
        if (repair == null) {
            repair = repository.save(mapper.mapToDetectedRepairs(defectRepairDto
                                                     , completedRepairsService.getById(defectRepairDto.getRepairId())
                                                     , vmsService.get(defectRepairDto.getTaskJournalId())));
            repair.setParametersMeasurements(parameterMeasurementService.save(new ParameterMeasurementBuilder.Builder()
                    .detectedRepairs(repair)
                    .parametersMeasurements(defectRepairDto.getParameters())
                    .build()));
        }
        return mapper.mapToResponseDefectRepairDto(repair);
    }

    @Override
    public ResponseDefectRepairDto update(DefectRepairDto defectRepairDto) {
        DetectedRepairs repair = getById(defectRepairDto.getId());
         repair = repository.save(mapper.mapToDetectedRepairs(defectRepairDto
                                , completedRepairsService.getById(defectRepairDto.getRepairId())
                                , repair.getVisualMeasuringSurvey()));
        repair.setParametersMeasurements(parameterMeasurementService.save(new ParameterMeasurementBuilder.Builder()
                .detectedRepairs(repair)
                .parametersMeasurements(defectRepairDto.getParameters())
                .build()));
        return mapper.mapToResponseDefectRepairDto(repair);
    }

    @Override
    public List<ResponseDefectRepairDto> getAll(Long id) {
        return repository.findAllByEquipmentDiagnosedId(id)
                         .stream()
                         .map(mapper::mapToResponseDefectRepairDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("DefectRepair with id=%s not found for delete", id));
    }

    private DetectedRepairs getByPredicate(DefectRepairDto defectRepairDto) {
        QDetectedRepairs repair = QDetectedRepairs.detectedRepairs;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(repair.elementId.eq(defectRepairDto.getElementId()));
        booleanBuilder.and(repair.repairId.eq(defectRepairDto.getRepairId()));
        if (defectRepairDto.getPartElementId() != null) {
            booleanBuilder.and(repair.partElementId.eq(defectRepairDto.getPartElementId()));
        }
        return new JPAQueryFactory(em).from(repair)
                .select(repair)
                .where(booleanBuilder)
                .fetchOne();
    }

    private DetectedRepairs getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("DefectRepair with id=%s not found", id)));
    }
}