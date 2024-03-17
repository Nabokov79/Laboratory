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
        pointDifferenceService.save(repository.saveAll(calculatedDeviation(measurements)));
    }

    private List<ControlPoint> calculatedDeviation(List<GeodesicMeasurement> measurements) {
        Integer min = getMinMeasurement(measurements);
        return measurements.stream()
                .map(m -> mapper.mapToControlPoint(m.getNumberMeasurementLocation()
                                                 , m.getControlPointValue()
                                                 , sum(min, m.getControlPointValue()))
                )
                .toList();
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