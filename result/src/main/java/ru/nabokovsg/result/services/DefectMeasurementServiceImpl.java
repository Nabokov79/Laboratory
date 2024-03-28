package ru.nabokovsg.result.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.defects.DefectMeasurementDto;
import ru.nabokovsg.result.dto.defects.ResponseDefectMeasurementDto;
import ru.nabokovsg.result.exceptions.NotFoundException;
import ru.nabokovsg.result.mappers.DefectMeasurementMapper;
import ru.nabokovsg.result.models.Defect;
import ru.nabokovsg.result.models.DefectMeasurement;
import ru.nabokovsg.result.models.QDefectMeasurement;
import ru.nabokovsg.result.models.builders.ParameterMeasurementBuilder;
import ru.nabokovsg.result.repository.DefectMeasurementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectMeasurementServiceImpl implements DefectMeasurementService {

    private final DefectMeasurementRepository repository;
    private final DefectMeasurementMapper mapper;
    private final ParameterMeasurementService parameterMeasurementService;
    private final EntityManager em;
    private final VisualMeasuringSurveyService vmsService;
    private final DefectsService defectsService;
    @Override
    public ResponseDefectMeasurementDto save(DefectMeasurementDto defectMeasurementDto) {
        DefectMeasurement defectMeasurement = getByPredicate(defectMeasurementDto);
        Defect defect = defectsService.get(defectMeasurementDto.getDefectId());
        if (defectMeasurement == null) {
            defectMeasurement = repository.save(
                    mapper.mapToDefectMeasurement(defectMeasurementDto
                                                , defect
                                                , vmsService.get(defectMeasurementDto.getTaskJournalId())));
        }
        defectMeasurement.setParametersMeasurements(parameterMeasurementService.save(
                new ParameterMeasurementBuilder.Builder()
                                               .defectParameters(defect.getParameters())
                                               .defectMeasurement(defectMeasurement)
                                               .parametersMeasurements(defectMeasurementDto.getParametersMeasurements())
                                               .build()));
        return mapper.mapToResponseDefectMeasurementDto(defectMeasurement);
    }

    @Override
    public ResponseDefectMeasurementDto update(DefectMeasurementDto defectMeasurementDto) {
        if (repository.existsById(defectMeasurementDto.getId())) {
            DefectMeasurement defectMeasurement = repository.save(
                                                           mapper.mapToDefectMeasurement(defectMeasurementDto
                                                          , defectsService.get(defectMeasurementDto.getDefectId())
                                                          , vmsService.get(defectMeasurementDto.getTaskJournalId())));
            defectMeasurement.setParametersMeasurements(parameterMeasurementService.update(
                                                new ParameterMeasurementBuilder.Builder()
                                               .defectMeasurement(defectMeasurement)
                                               .parametersMeasurements(defectMeasurementDto.getParametersMeasurements())
                                               .build()));
            return mapper.mapToResponseDefectMeasurementDto(defectMeasurement);
        }
        throw new NotFoundException(
                String.format("Defect measurement with id=%s not found for update", defectMeasurementDto.getId()));
    }

    @Override
    public List<ResponseDefectMeasurementDto> getAll(Long id) {
        return repository.findAllByEquipmentDiagnosedId(id)
                         .stream()
                         .map(mapper::mapToResponseDefectMeasurementDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Defect measurement with id=%s not found for delete", id));
    }

    private DefectMeasurement getByPredicate(DefectMeasurementDto defectMeasurementDto) {
        QDefectMeasurement defect = QDefectMeasurement.defectMeasurement;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(defect.elementId.eq(defectMeasurementDto.getElementId()));
        booleanBuilder.and(defect.defectId.eq(defectMeasurementDto.getDefectId()));
        if (defectMeasurementDto.getPartElementId() != null) {
            booleanBuilder.and(defect.partElementId.eq(defectMeasurementDto.getPartElementId()));
        }
        return new JPAQueryFactory(em).from(defect)
                .select(defect)
                .where(booleanBuilder)
                .fetchOne();
    }
}