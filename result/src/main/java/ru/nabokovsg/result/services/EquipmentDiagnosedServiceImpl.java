package ru.nabokovsg.result.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.equipmentDiagnosed.EquipmentDiagnosedDto;
import ru.nabokovsg.result.exceptions.NotFoundException;
import ru.nabokovsg.result.mappers.EquipmentDiagnosedMapper;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.QEquipmentDiagnosed;
import ru.nabokovsg.result.models.builders.SearchParametersBuilder;
import ru.nabokovsg.result.repository.EquipmentDiagnosedRepository;

@Service
@RequiredArgsConstructor
public class EquipmentDiagnosedServiceImpl implements EquipmentDiagnosedService {

    private final EquipmentDiagnosedRepository repository;
    private final EquipmentDiagnosedMapper mapper;
    private final EntityManager em;

    @Override
    public void save(EquipmentDiagnosedDto equipmentDto) {
        if (get(equipmentDto.getId(), equipmentDto.getEquipmentId()) == null) {
            EquipmentDiagnosed equipmentDiagnosed = mapper.mapToEquipmentDiagnosed(equipmentDto);
            repository.save(equipmentDiagnosed);
        }
    }

    @Override
    public EquipmentDiagnosed getEquipmentDiagnosedData(SearchParametersBuilder parameters) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QEquipmentDiagnosed equipmentDiagnosed = QEquipmentDiagnosed.equipmentDiagnosed;
        if (parameters.getTaskJournalId() != null) {
            booleanBuilder.and(equipmentDiagnosed.taskJournalId.eq(parameters.getTaskJournalId()));
        }
        if (parameters.getEquipmentId() != null) {
            booleanBuilder.and(equipmentDiagnosed.equipmentId.eq(parameters.getEquipmentId()));
        }
        if (parameters.getFull() != null) {
            booleanBuilder.and(equipmentDiagnosed.full.eq(parameters.getFull()));
        }
        return new JPAQueryFactory(em).from(equipmentDiagnosed)
                                      .select(equipmentDiagnosed)
                                      .where(booleanBuilder)
                                      .stream()
                                      .findFirst()
                                      .orElseThrow(() -> new NotFoundException(
                                       String.format("EquipmentDiagnosed by parameters taskJournalId=%s, equipmentId=%s"
                                                                                         , parameters.getTaskJournalId()
                                                                                         , parameters.getEquipmentId()))
                                      );
    }

    private EquipmentDiagnosed get(Long taskJournalId, Long equipmentId) {
       return repository.findByTaskJournalIdAndEquipmentId(taskJournalId, equipmentId);
    }
}