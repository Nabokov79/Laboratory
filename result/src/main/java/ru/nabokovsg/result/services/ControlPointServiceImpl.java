package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.mappers.ControlPointMapper;
import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.ControlPointMeasurement;
import ru.nabokovsg.result.models.GeodesicMeasurement;
import ru.nabokovsg.result.repository.ControlPointRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ControlPointServiceImpl implements ControlPointService {

    private final ControlPointRepository repository;
    private final ControlPointMapper mapper;
    private final CalculatingPointMeasurementService calculatingPointService;

    @Override
    public List<ControlPoint> save(ControlPointMeasurement controlPointMeasurement, List<GeodesicMeasurement> measurements) {
        Integer min = calculatingPointService.getMinMeasurement(measurements.stream().map(GeodesicMeasurement::getControlPointValue).toList());
        return repository.saveAll(measurements.stream()
                                              .map(m -> mapper.mapToControlPoint(
                                                                            m.getId()
                                                                            , m.getNumberMeasurementLocation()
                                                                            , m.getControlPointValue()
                                                                            , calculatingPointService.getDeviation(min
                                                                                    , m.getControlPointValue())
                                              , controlPointMeasurement))
                                              .toList());
    }

    @Override
    public List<ControlPoint> update(ControlPointMeasurement controlPointMeasurement, List<GeodesicMeasurement> measurements) {
        Integer min = calculatingPointService.getMinMeasurement(measurements.stream().map(GeodesicMeasurement::getControlPointValue).toList());
        return measurements.stream()
                           .map(m -> mapper.mapToControlPoint(m.getId()
                                                , m.getNumberMeasurementLocation()
                                                , m.getControlPointValue()
                                                , calculatingPointService.getDeviation(min, m.getControlPointValue())
                           , controlPointMeasurement))
                          .toList();
    }
}