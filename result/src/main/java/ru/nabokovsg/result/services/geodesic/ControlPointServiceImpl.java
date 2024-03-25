package ru.nabokovsg.result.services.geodesic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.mappers.ControlPointMapper;
import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.ControlPointMeasurement;
import ru.nabokovsg.result.models.GeodesicMeasurement;
import ru.nabokovsg.result.repository.ControlPointRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ControlPointServiceImpl implements ControlPointService {

    private final ControlPointRepository repository;
    private final ControlPointMapper mapper;
    private final CalculationGeodesyPointsService calculatingPointService;

    @Override
    public Set<ControlPoint> save(ControlPointMeasurement controlPointMeasurement, List<GeodesicMeasurement> measurements) {
        Integer min = calculatingPointService.getMinMeasurement(measurements.stream()
                                                                         .map(GeodesicMeasurement::getControlPointValue)
                                                                         .toList());
        return new HashSet<>(repository.saveAll(measurements.stream()
                                                            .map(m -> mapper.mapToControlPoint(
                                                                      m.getNumberMeasurementLocation()
                                                                    , m.getControlPointValue()
                                                                    , calculatingPointService.getDeviation(min
                                                                            , m.getControlPointValue())
                                                                    , controlPointMeasurement))
                                                            .toList()));
    }

    @Override
    public Set<ControlPoint> update(ControlPointMeasurement controlPointMeasurement
                                  , List<GeodesicMeasurement> measurements) {
        Integer min = calculatingPointService.getMinMeasurement(measurements.stream()
                                                                         .map(GeodesicMeasurement::getControlPointValue)
                                                                         .toList());
       Map<Integer, Long> controlPoints =
                                          repository.findAllByControlPointMeasurementId(controlPointMeasurement.getId())
                                                    .stream()
                                                    .collect(Collectors.toMap(ControlPoint::getPlaceNumber, ControlPoint::getId));
       List<ControlPoint> points = measurements.stream()
               .map(m -> mapper.mapToUpdateControlPoint(controlPoints.get(m.getNumberMeasurementLocation())
                       , m.getNumberMeasurementLocation()
                       , m.getControlPointValue()
                       , calculatingPointService.getDeviation(min, m.getControlPointValue())
                       , controlPointMeasurement))
               .toList();
        return new HashSet<>(repository.saveAll(points));
    }
}