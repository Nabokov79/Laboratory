package ru.nabokovsg.result.services.geodesic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.сontrolPoint.ControlPointMeasurementDto;
import ru.nabokovsg.result.exceptions.BadRequestException;
import ru.nabokovsg.result.exceptions.NotFoundException;
import ru.nabokovsg.result.mappers.ControlPointMeasurementMapper;
import ru.nabokovsg.result.models.*;
import ru.nabokovsg.result.models.builders.MeasurementBuilder;
import ru.nabokovsg.result.repository.ControlPointMeasurementRepository;

@Service
@RequiredArgsConstructor
public class ControlPointMeasurementServiceImpl implements ControlPointMeasurementService {

    private final ControlPointMeasurementRepository repository;
    private final ControlPointMeasurementMapper mapper;
    private final ControlPointService controlPointService;
    private final PointDifferenceService pointDifferenceService;

    @Override
    public void save(MeasurementBuilder builder) {
        ControlPointMeasurement controlPointMeasurement = repository.findByEquipmentDiagnosedId(
                                                                               builder.getEquipmentDiagnosed().getId());
        if (controlPointMeasurement != null) {
            throw new BadRequestException(
                    String.format("ControlPointMeasurement by equipmentDiagnosedId=%s not found for update"
                                                                            , builder.getEquipmentDiagnosed().getId()));
        }
        controlPointMeasurement = repository.save(mapper.mapToControlPointMeasurement(
                                     new MeasurementBuilder.Builder()
                                                           .equipmentDiagnosed(builder.getEquipmentDiagnosed())
                                                           .build()));
        pointDifferenceService.save(new MeasurementBuilder.Builder()
                .controlPointMeasurement(builder.getControlPointMeasurement())
                .controlPointMeasurement(controlPointMeasurement)
                .controlPoints(controlPointService.save(controlPointMeasurement, builder.getGeodesicMeasurements()))
                .permissibleDeviations(builder.getPermissibleDeviations())
                .build()
        );
    }

    @Override
    public void update(MeasurementBuilder builder) {
        ControlPointMeasurement controlPointMeasurement = repository.findByEquipmentDiagnosedId(
                                                                               builder.getEquipmentDiagnosed().getId());
        if (controlPointMeasurement != null) {
            controlPointMeasurement = repository.save(mapper.mapToControlPointMeasurement(
                                              new MeasurementBuilder.Builder()
                                                                    .equipmentDiagnosed(builder.getEquipmentDiagnosed())
                                                                    .build()));
            pointDifferenceService.save(new MeasurementBuilder.Builder()
                    .controlPointMeasurement(builder.getControlPointMeasurement())
                    .controlPointMeasurement(controlPointMeasurement)
                    .controlPoints(controlPointService.save(controlPointMeasurement, builder.getGeodesicMeasurements()))
                    .permissibleDeviations(builder.getPermissibleDeviations())
                    .build()
            );
        }
        throw new NotFoundException(
                String.format("ControlPointMeasurement by equipmentDiagnosedId=%s not found for update"
                                                                              , builder.getEquipmentDiagnosed().getId())
                );
    }

    @Override
    public ControlPointMeasurementDto get(Long id) {
        ControlPointMeasurement measurement = getByEquipmentDiagnosedId(id);
        if (measurement == null) {
            throw new NotFoundException(
                    String.format("ControlPointMeasurement by equipmentDiagnosedId=%s not found for update", id)
            );
        }
        return mapper.mapToControlPointMeasurementDto(measurement);
    }

    private ControlPointMeasurement getByEquipmentDiagnosedId(Long equipmentDiagnosedId) {
        return repository.findByEquipmentDiagnosedId(equipmentDiagnosedId);
    }
}