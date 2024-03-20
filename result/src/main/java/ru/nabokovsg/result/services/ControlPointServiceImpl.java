package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.mappers.ControlPointMapper;
import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.GeodesicMeasurement;
import ru.nabokovsg.result.repository.ControlPointRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ControlPointServiceImpl implements ControlPointService {

    private final ControlPointRepository repository;
    private final ControlPointMapper mapper;
    private final PointDifferenceService pointDifferenceService;

    @Override
    public void save(List<GeodesicMeasurement> measurements) {
        Integer min = getMinMeasurement(measurements);
        List<ControlPoint> points = measurements.stream()
                                                .map(m -> mapper.mapToControlPoint(m.getId()
                                                                                 , m.getNumberMeasurementLocation()
                                                                                 , m.getControlPointValue()
                                                                                 , sum(min, m.getControlPointValue())))
                                                .toList();
        pointDifferenceService.save(repository.saveAll(points));
    }

    @Override
    public void update(List<GeodesicMeasurement> measurements) {
        Integer min = getMinMeasurement(measurements);
        List<ControlPoint> points = measurements.stream()
                .map(m -> mapper.mapToControlPoint(m.getId()
                        , m.getNumberMeasurementLocation()
                        , m.getControlPointValue()
                        , sum(min, m.getControlPointValue())))
                .toList();
        pointDifferenceService.update(repository.saveAll(points));
    }

    private Integer getMinMeasurement(List<GeodesicMeasurement> measurements) {
        Integer min = 0;
        for (GeodesicMeasurement measurement: measurements) {
            if (min == 0 || measurement.getControlPointValue() < min) {
                min = measurement.getControlPointValue();
            }
        }
        return min;
    }

    private Integer sum(Integer min, Integer controlPointValue) {
        return min - controlPointValue;
    }
}