package ru.nabokovsg.result.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.result.dto.visualInspection.VisualInspectionDto;
import ru.nabokovsg.result.mappers.VisualInspectionMapper;
import ru.nabokovsg.result.models.QEquipmentDiagnosed;
import ru.nabokovsg.result.models.QVisualInspection;
import ru.nabokovsg.result.models.VisualInspection;
import ru.nabokovsg.result.repository.VisualInspectionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisualInspectionServiceImpl implements VisualInspectionService {

    private final VisualInspectionRepository repository;
    private final VisualInspectionMapper mapper;
    private final VisualMeasuringSurveyService vmsService;
    private final EntityManager em;

    @Override
    public ResponseVisualInspectionDto save(VisualInspectionDto inspectionDto) {
        VisualInspection inspection = getByPredicate(inspectionDto);
        return null;
    }

    @Override
    public ResponseVisualInspectionDto update(VisualInspectionDto inspectionDto) {
        return null;
    }

    @Override
    public List<ResponseVisualInspectionDto> getAll(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private VisualInspection getByPredicate(VisualInspectionDto inspectionDto) {
        QVisualInspection inspection = QVisualInspection.visualInspection;
        QEquipmentDiagnosed equipment = QEquipmentDiagnosed.equipmentDiagnosed;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(equipment.taskJournalId.eq(inspectionDto.getTaskJournalId()));
        booleanBuilder.and(equipment.equipmentId.eq(inspectionDto.getEquipmentId()));
        booleanBuilder.and(inspection.elementId.eq(inspectionDto.getElementId()));
        booleanBuilder.and(inspection.inspection.eq(inspectionDto.getInspection()));
        return new JPAQueryFactory(em).from(inspection)
                                      .select(inspection)
                                      .where(booleanBuilder)
                                      .fetchOne();
    }
}