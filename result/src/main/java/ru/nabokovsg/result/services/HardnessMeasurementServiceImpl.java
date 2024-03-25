package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.acceptable.FullAcceptableThicknessDto;
import ru.nabokovsg.result.dto.hardnessMeasurement.HardnessMeasurementDataDto;
import ru.nabokovsg.result.dto.hardnessMeasurement.HardnessMeasurementDto;
import ru.nabokovsg.result.dto.hardnessMeasurement.ResponseHardnessMeasurementDto;
import ru.nabokovsg.result.exceptions.NotFoundException;
import ru.nabokovsg.result.mappers.HardnessMeasurementMapper;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.HardnessMeasurement;
import ru.nabokovsg.result.models.builders.SearchParametersBuilder;
import ru.nabokovsg.result.repository.HardnessMeasurementRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HardnessMeasurementServiceImpl implements HardnessMeasurementService {

    private final HardnessMeasurementRepository repository;
    private final HardnessMeasurementMapper mapper;
    private final EquipmentDiagnosedService equipmentDiagnosedService;
    private final AcceptableThicknessService acceptableThicknessService;

    @Override
    public List<ResponseHardnessMeasurementDto> save(HardnessMeasurementDataDto measurementsDto) {
        Map<Integer, HardnessMeasurement> measurements =
        repository.findAllByEquipmentDiagnosedTaskJournalIdAndEquipmentDiagnosedEquipmentId(
                                                                                      measurementsDto.getTaskJournalId()
                                                                                    , measurementsDto.getEquipmentId())
                                          .stream()
                                          .collect(Collectors.toMap(HardnessMeasurement::getMeasurementNumber, h -> h));
        EquipmentDiagnosed equipmentDiagnosed = equipmentDiagnosedService.getEquipmentDiagnosedData(
                                         new SearchParametersBuilder.SearchParameters()
                                                                    .taskJournalId(measurementsDto.getTaskJournalId())
                                                                    .equipmentId(measurementsDto.getEquipmentId())
                                                                    .build());
        List<FullAcceptableThicknessDto> acceptableThickness =
                                                acceptableThicknessService.getAll(measurementsDto.getEquipmentTypeId());
        measurementsDto.getHardnessMeasurements().forEach(m -> {
            HardnessMeasurement measurement = measurements.get(m.getMeasurementNumber());
            if (measurement == null) {
                measurement = mapper.mapToHardnessMeasurement(m, equipmentDiagnosed);
            } else {
                if (measurement.getMeasurementValue() > m.getMeasurementValue()) {
                    measurement.setMeasurementValue(calculateAverageValue(
                            List.of(m.getMeasurementValue(), measurement.getMeasurementValue()))
                    );
                }
            }
            measurements.put(measurement.getMeasurementNumber()
                    , mapper.mapHardnessMeasurementWithAcceptableValue(measurement
                                                        , getAcceptableValue(getAcceptableThickness(acceptableThickness
                                                                                                  , m.getPartElementId()
                                                                                                  , m.getDiameter())
                                                                            , measurement)));
        });
        return repository.saveAll(measurements.values())
                         .stream()
                         .map(mapper::mapToResponseHardnessMeasurementDto)
                         .toList();
    }

    @Override
    public List<ResponseHardnessMeasurementDto> update(List<HardnessMeasurementDto> measurementsDto) {
        List<Long> ids = measurementsDto.stream().map(HardnessMeasurementDto::getId).toList();
        Map<Long, HardnessMeasurement> measurements = repository.findAllById(ids)
                                                        .stream()
                                                        .collect(Collectors.toMap(HardnessMeasurement::getId, h -> h));
        if (measurementsDto.size() != measurements.size()) {
            throw new NotFoundException(String.format("Hardness Measurement with ids=%s not found for update", ids));
        }
        List<FullAcceptableThicknessDto> acceptableThickness = acceptableThicknessService.getAll(
                                                    measurements.values()
                                                               .stream()
                                                               .map(h -> h.getEquipmentDiagnosed().getEquipmentTypeId())
                                                               .collect(Collectors.toSet())
                                                               .iterator()
                                                               .next()
        );
        return repository.saveAll(
                measurementsDto.stream()
                               .map(m -> mapper.mapToHardnessMeasurement(m
                                                                 , measurements.get(m.getId()).getEquipmentDiagnosed()))
                               .map(m -> mapper.mapHardnessMeasurementWithAcceptableValue(m
                                        , getAcceptableValue(getAcceptableThickness(acceptableThickness
                                                                                  , m.getPartElementId()
                                                                                  , m.getDiameter())
                                        , m)))
                               .toList())
                .stream()
                .map(mapper::mapToResponseHardnessMeasurementDto)
                .toList();
    }

    @Override
    public List<ResponseHardnessMeasurementDto> getAll(Long id) {
        return repository.findAllByEquipmentDiagnosedTaskJournalId(id)
                         .stream()
                         .map(mapper::mapToResponseHardnessMeasurementDto)
                         .toList();
    }

    private FullAcceptableThicknessDto getAcceptableThickness(List<FullAcceptableThicknessDto> acceptableThicknessDto
                                                            , Long partElementId
                                                            , Integer diameter) {
        if (diameter != null) {
            return acceptableThicknessDto.stream()
                                         .collect(Collectors.toMap(FullAcceptableThicknessDto::getDiameter, a -> a))
                                         .get(diameter);
        }
        return acceptableThicknessDto.stream()
                                     .collect(Collectors.toMap(FullAcceptableThicknessDto::getPartElementId, a -> a))
                                     .get(partElementId);
    }

    private boolean getAcceptableValue(FullAcceptableThicknessDto acceptableThickness
                                                    , HardnessMeasurement measurement) {
        if (acceptableThickness == null) {
            return false;
        } else {
            return measurement.getMeasurementValue() < acceptableThickness.getMinHardness() ||
                    measurement.getMeasurementValue() > acceptableThickness.getMaxHardness();
        }
    }

    private Integer calculateAverageValue(List<Integer> measurementValues) {
        return (int) Math.round((double) measurementValues.stream().mapToInt(a -> a).sum()/measurementValues.size());
    }
}