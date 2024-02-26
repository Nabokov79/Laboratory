package ru.nabokovsg.lab_nk.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.dto.measuringTool.FullMeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.RequestParameters;
import ru.nabokovsg.lab_nk.exceptions.NotFoundException;
import ru.nabokovsg.lab_nk.mappers.MeasuringToolMapper;
import ru.nabokovsg.lab_nk.models.MeasuringTool;
import ru.nabokovsg.lab_nk.models.QMeasuringTool;
import ru.nabokovsg.lab_nk.repository.MeasuringToolRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasuringToolServiceImpl implements MeasuringToolService {

    private final MeasuringToolRepository repository;
    private final MeasuringToolMapper mapper;
    private final EntityManager em;
    private final LaboratoryEmployeeService employeeService;

    @Override
    public FullMeasuringToolDto save(MeasuringToolDto measuringToolDto) {
        MeasuringTool measuringTool = getByPredicate(measuringToolDto);
        if (measuringTool == null) {
            measuringTool = mapper.mapToMeasuringTool(measuringToolDto);
            if (measuringToolDto.getEmployeeId() != null) {
                measuringTool = mapper.mapMeasuringToolWithLaboratoryEmployee(measuringTool
                                                           , employeeService.getById(measuringToolDto.getEmployeeId()));
            }
        }
        return mapper.mapToFullMeasuringToolDto(repository.save(measuringTool));
    }

    @Override
    public FullMeasuringToolDto update(MeasuringToolDto measuringToolDto) {
        if (repository.existsById(measuringToolDto.getId())) {
            MeasuringTool measuringTool = mapper.mapToMeasuringTool(measuringToolDto);
            if (measuringToolDto.getEmployeeId() != null) {
                measuringTool = mapper.mapMeasuringToolWithLaboratoryEmployee(measuringTool
                                                           , employeeService.getById(measuringToolDto.getEmployeeId()));
            }
            return mapper.mapToFullMeasuringToolDto(repository.save(measuringTool));
        }
        throw new NotFoundException(
                String.format("MeasuringTool with id=%s not found for update", measuringToolDto.getId())
        );
    }

    @Override
    public List<FullMeasuringToolDto> getAll(RequestParameters parameters) {
        QMeasuringTool measuringTool = QMeasuringTool.measuringTool;
        return new JPAQueryFactory(em).from(measuringTool)
                                      .select(measuringTool)
                                      .where(getPredicate(mapper.mapToMeasuringToolDto(parameters)))
                                      .fetch()
                                      .stream()
                                      .map(mapper::mapToFullMeasuringToolDto)
                                      .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("measuring tool with id = %s not found for delete", id));
    }

    private MeasuringTool getByPredicate(MeasuringToolDto measuringToolDto) {
        QMeasuringTool measuringTool = QMeasuringTool.measuringTool;
        return new JPAQueryFactory(em).from(measuringTool)
                .select(measuringTool)
                .where(getPredicate(measuringToolDto))
                .fetchOne();
    }

    private BooleanBuilder getPredicate(MeasuringToolDto measuringToolDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (measuringToolDto.getToll() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.toll.eq(measuringToolDto.getToll()));
        }
        if (measuringToolDto.getModel() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.model.eq(measuringToolDto.getModel()));
        }
        if (measuringToolDto.getWorkNumber() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.workNumber.eq(measuringToolDto.getWorkNumber()));
        }
        if (measuringToolDto.getManufacturing() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.manufacturing.eq(measuringToolDto.getManufacturing()));
        }
        if (measuringToolDto.getExploitation() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.exploitation.eq(measuringToolDto.getExploitation()));
        }
        if (measuringToolDto.getManufacturer() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.manufacturer.eq(measuringToolDto.getManufacturer()));
        }
        if (measuringToolDto.getCertificateNumber() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.organization.eq(measuringToolDto.getCertificateNumber()));
        }
        if (measuringToolDto.getEmployeeId() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.employee.id.eq(measuringToolDto.getEmployeeId()));
        }
       return booleanBuilder;
    }
}