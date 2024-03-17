package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.geodesic.GeodesicMeasurementDto;
import ru.nabokovsg.result.dto.geodesic.GeodeticMeasurementEquipmentDto;
import ru.nabokovsg.result.dto.geodesic.ResponseGeodesicMeasurementDto;
import ru.nabokovsg.result.exceptions.BadRequestException;
import ru.nabokovsg.result.exceptions.NotFoundException;
import ru.nabokovsg.result.mappers.GeodesicMeasurementMapper;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.GeodesicMeasurement;
import ru.nabokovsg.result.repository.GeodesicMeasurementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeodesicMeasurementServiceImpl implements GeodesicMeasurementService {

    private final GeodesicMeasurementRepository repository;
    private final GeodesicMeasurementMapper mapper;
    private final EquipmentDiagnosedService equipmentDiagnosedService;
    private final CalculatingGeodesicMeasurementService calculateService;

    @Override
    public List<ResponseGeodesicMeasurementDto> save(GeodeticMeasurementEquipmentDto measurementsDto) {
        List<GeodesicMeasurementDto> measurements = measurementsDto.getMeasurements()
                .stream()
                .filter(m ->
                        !repository.findAllSequentialNumberByTaskJournalId(measurementsDto.getEquipmentDiagnose().getId())
                                                                                    .contains(m.getSequentialNumber()))
                .toList();
        if (measurements.isEmpty()) {
            throw new BadRequestException(
                    String.format("Geodetic measurement equipment by TaskJournal with id=%s found. Update the results"
                            , measurementsDto.getEquipmentDiagnose().getId())
            );
        }
        EquipmentDiagnosed equipmentDiagnosed = equipmentDiagnosedService.addGeodeticMeasurementData(measurementsDto);
        List<GeodesicMeasurement> geodesicMeasurements = repository.saveAll(measurements.stream()
                                                        .map(m -> mapper.mapToGeodesicMeasurement(m, equipmentDiagnosed))
                                                        .toList());
        calculateService.calculate(equipmentDiagnosed, geodesicMeasurements);
        return geodesicMeasurements.stream()
                                   .map(mapper::mapToResponseGeodesicMeasurementDto)
                                   .toList();

    }

    @Override
    public ResponseGeodesicMeasurementDto update(GeodesicMeasurementDto measurementDto) {
        return mapper.mapToResponseGeodesicMeasurementDto(
                repository.save(mapper.mapToGeodesicMeasurement(measurementDto
                                                             , getById(measurementDto.getId()).getEquipmentDiagnosed()))
        );
    }

    @Override
    public List<ResponseGeodesicMeasurementDto> getAll(Long id) {
        return repository.findAllByTaskJournalId(id)
                         .stream()
                         .map(mapper::mapToResponseGeodesicMeasurementDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("GeodesicMeasurement with id=%s not found for delete", id));
    }

    private GeodesicMeasurement getById(Long id) {
        return repository.findById(id)
               .orElseThrow(() -> new NotFoundException(String.format("GeodesicMeasurement with id=%s not found", id)));
    }
}