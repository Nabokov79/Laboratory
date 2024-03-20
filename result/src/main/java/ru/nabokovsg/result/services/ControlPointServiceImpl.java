package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.mappers.ControlPointMapper;
import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.GeodesicMeasurement;
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;
import ru.nabokovsg.result.repository.ControlPointRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ControlPointServiceImpl implements ControlPointService {

    private final ControlPointRepository repository;
    private final ControlPointMapper mapper;
    private final PointDifferenceService pointDifferenceService;
    private final CalculatingPointMeasurementService calculatingPointService;

    @Override
    public void save(List<GeodesicMeasurement> measurements, PermissibleDeviationsGeodesy permissibleDeviations) {
        Integer min = calculatingPointService.getMinMeasurement(measurements);
        List<ControlPoint> points = measurements.stream()
                                                .map(m -> mapper.mapToControlPoint(
                                                        m.getId()
                                                      , m.getNumberMeasurementLocation()
                                                      , m.getControlPointValue()
                                                      , calculatingPointService.getDeviation(min
                                                                                           , m.getControlPointValue())))
                                                .toList();
        pointDifferenceService.save(repository.saveAll(points));
    }

    @Override
    public void update(List<GeodesicMeasurement> measurements, PermissibleDeviationsGeodesy permissibleDeviations) {
        Integer min = calculatingPointService.getMinMeasurement(measurements);
        List<ControlPoint> points = measurements.stream()
                                         .map(m -> mapper.mapToControlPoint(m.getId()
                                                 , m.getNumberMeasurementLocation()
                                                 , m.getControlPointValue()
                                                 , calculatingPointService.getDeviation(min, m.getControlPointValue())))
                                         .toList();
        pointDifferenceService.update(repository.saveAll(points));
    }
}