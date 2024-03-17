package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.equipmentDiagnosed.EquipmentDiagnosedDto;
import ru.nabokovsg.result.dto.geodesic.GeodeticMeasurementEquipmentDto;
import ru.nabokovsg.result.exceptions.NotFoundException;
import ru.nabokovsg.result.mappers.EquipmentDiagnosedMapper;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.repository.EquipmentDiagnosedRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentDiagnosedServiceImpl implements EquipmentDiagnosedService {

    private final EquipmentDiagnosedRepository repository;
    private final EquipmentDiagnosedMapper mapper;

    @Override
    public void save(EquipmentDiagnosedDto equipmentDto) {
        if (getByParameters(equipmentDto.getId(), equipmentDto.getEquipmentId()).isPresent()) {
            repository.save(mapper.mapToEquipmentDiagnosed(equipmentDto));
        }
    }

    @Override
    public EquipmentDiagnosed addGeodeticMeasurementData(GeodeticMeasurementEquipmentDto equipmentDto) {
        EquipmentDiagnosed equipmentDiagnosed = getByParameters(equipmentDto.getEquipmentDiagnose().getId()
                , equipmentDto.getEquipmentDiagnose().getEquipmentId()).orElseThrow(() -> new NotFoundException(
                String.format("EquipmentDiagnosed by parameters taskJournalId=%s and equipmentId=%s not found"
                        , equipmentDto.getEquipmentDiagnose().getId(), equipmentDto.getEquipmentDiagnose().getEquipmentId())));
        equipmentDiagnosed.setFull(equipmentDto.getFull());
        return repository.save(equipmentDiagnosed);
    }

    private Optional<EquipmentDiagnosed> getByParameters(Long taskJournalId, Long equipmentId) {
        return repository.findByTaskJournalIdAndEquipmentId(taskJournalId, equipmentId);
    }
}