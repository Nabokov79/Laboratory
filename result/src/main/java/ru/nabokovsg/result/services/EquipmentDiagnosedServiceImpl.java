package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.equipmentDiagnosed.EquipmentDiagnosedDto;
import ru.nabokovsg.result.exceptions.NotFoundException;
import ru.nabokovsg.result.mappers.EquipmentDiagnosedMapper;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.repository.EquipmentDiagnosedRepository;

@Service
@RequiredArgsConstructor
public class EquipmentDiagnosedServiceImpl implements EquipmentDiagnosedService {

    private final EquipmentDiagnosedRepository repository;
    private final EquipmentDiagnosedMapper mapper;

    @Override
    public void save(EquipmentDiagnosedDto equipmentDto) {
        if (get(equipmentDto.getId(), equipmentDto.getEquipmentId()) == null) {
            EquipmentDiagnosed equipmentDiagnosed = mapper.mapToEquipmentDiagnosed(equipmentDto);
            repository.save(equipmentDiagnosed);
        }
    }

    @Override
    public EquipmentDiagnosed getEquipmentDiagnosedData(Long taskJournalId, Long equipmentId, Boolean full) {
        EquipmentDiagnosed equipmentDiagnosed = get(taskJournalId, equipmentId);
        if (equipmentDiagnosed != null) {
            return repository.save(mapper.mapToParamFull(get(taskJournalId, equipmentId), full));
        }
        throw new NotFoundException(
                String.format("EquipmentDiagnosed by parameters taskJournalId=%s, equipmentId=%s", taskJournalId
                        , equipmentId));


    }

    private EquipmentDiagnosed get(Long taskJournalId, Long equipmentId) {
       return repository.findByTaskJournalIdAndEquipmentId(taskJournalId, equipmentId);
    }
}