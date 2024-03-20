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
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;
import ru.nabokovsg.result.repository.GeodesicMeasurementRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeodesicMeasurementServiceImpl implements GeodesicMeasurementService {

    private final GeodesicMeasurementRepository repository;
    private final GeodesicMeasurementMapper mapper;
    private final EquipmentDiagnosedService equipmentDiagnosedService;
    private final ReferencePointService referencePointService;
    private final ControlPointService controlPointService;
    private final PermissibleDeviationsGeodesyService geodesyService;
    private final CalculatingGeodesicMeasurementService calculatingService;

    @Override
    public List<ResponseGeodesicMeasurementDto> save(GeodeticMeasurementEquipmentDto measurementsDto) {
        Map<Integer, GeodesicMeasurement> measurements = repository.findAllByTaskJournalId(
                        measurementsDto.getId()).stream()
                .collect(Collectors.toMap(GeodesicMeasurement::getNumberMeasurementLocation, g -> g)
                );
        if (measurements.size() != measurementsDto.getMeasurements().size() || measurements.isEmpty()) {
            measurementsDto.setMeasurements(measurementsDto.getMeasurements()
                    .stream()
                    .filter(m -> !measurements.containsKey(m.getNumberMeasurementLocation()))
                    .toList());
            if (!measurementsDto.getMeasurements().isEmpty()) {
                EquipmentDiagnosed equipmentDiagnosed =
                        equipmentDiagnosedService.getEquipmentDiagnosedData(measurementsDto.getId()
                                , measurementsDto.getEquipmentId()
                                , measurementsDto.getFull()
                        );
                repository.saveAll(measurementsDto.getMeasurements()
                                .stream()
                                .map(m -> mapper.mapToGeodesicMeasurement(m, equipmentDiagnosed))
                                .toList())
                        .forEach(m -> measurements.put(m.getNumberMeasurementLocation(), m));
                countingMeasurementPoints(equipmentDiagnosed, new ArrayList<>(measurements.values()), true);
            }
        } else {
            throw new BadRequestException(
                    String.format("Geodetic measurement equipment by TaskJournal with id=%s found. Update the results"
                            , measurementsDto.getId())
            );
        }
        return measurements.values()
                .stream()
                .map(mapper::mapToResponseGeodesicMeasurementDto)
                .toList();
    }


    @Override
    public List<ResponseGeodesicMeasurementDto> update(List<GeodesicMeasurementDto> measurementsDto) {
        List<Long> ids = measurementsDto.stream()
                .map(GeodesicMeasurementDto::getId)
                .toList();
        List<GeodesicMeasurement> measurements = repository.findAllById(ids);
        EquipmentDiagnosed equipmentDiagnosed = measurements.stream()
                .map(GeodesicMeasurement::getEquipmentDiagnosed)
                .collect(Collectors.toSet())
                .iterator()
                .next();
        if (measurementsDto.size() == measurements.size()) {
            measurements = repository.saveAll(measurementsDto.stream()
                    .map(m -> mapper.mapToGeodesicMeasurement(m, equipmentDiagnosed))
                    .toList());
            countingMeasurementPoints(equipmentDiagnosed, measurements, false);
            return measurements.stream()
                    .map(mapper::mapToResponseGeodesicMeasurementDto)
                    .toList();
        }
        throw new NotFoundException(
                String.format("Geodetic measurement equipment by ids=%s not found for update", ids));
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

    private void countingMeasurementPoints(EquipmentDiagnosed equipmentDiagnosed, List<GeodesicMeasurement> measurements, boolean flag) {
        PermissibleDeviationsGeodesy permissibleDeviations = geodesyService.getByParameters(
                                                                              equipmentDiagnosed.getEquipmentTypeId()
                                                                            , equipmentDiagnosed.getFull()
                                                                            , equipmentDiagnosed.getEquipmentOld());
        if (measurements.size() == permissibleDeviations.getNumberMeasurements()) {
            List<GeodesicMeasurement> geodesicMeasurements = calculatingService.recalculateMeasurements(measurements);
            if (flag) {
                referencePointService.save(equipmentDiagnosed, geodesicMeasurements, permissibleDeviations);
                controlPointService.save(geodesicMeasurements, permissibleDeviations);
            } else {
                referencePointService.update(equipmentDiagnosed, geodesicMeasurements, permissibleDeviations);
                controlPointService.update(geodesicMeasurements, permissibleDeviations);
            }
        }
    }
}