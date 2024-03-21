package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.mappers.PointDifferenceMapper;
import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.ControlPointMeasurement;
import ru.nabokovsg.result.models.PointDifference;
import ru.nabokovsg.result.models.enums.GeodesicPointType;
import ru.nabokovsg.result.repository.PointDifferenceRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PointDifferenceServiceImpl implements PointDifferenceService {

    private final PointDifferenceRepository repository;
    private final PointDifferenceMapper mapper;

    @Override
    public void save(ControlPointMeasurement controlPointMeasurement, List<ControlPoint> controlPoints) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                .collect(Collectors.toMap(ControlPoint::getPlaceNumber, c -> c));
        repository.saveAll(Stream.of(calculatedNeighboringPoints(points), calculatedDiametricalPoints(points))
                             .flatMap(Collection::stream)
                             .map(m -> mapper.mapPointDifferenceWithControlPointMeasurement(m, controlPointMeasurement))
                             .toList());


    }
    @Override
    public void update(ControlPointMeasurement controlPointMeasurement, List<ControlPoint> controlPoints) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                .collect(Collectors.toMap(ControlPoint::getPlaceNumber, c -> c));
        repository.saveAll(Stream.of(calculatedNeighboringPoints(points), calculatedDiametricalPoints(points))
                .flatMap(Collection::stream)
                .map(m -> mapper.mapPointDifferenceWithControlPointMeasurement(m, controlPointMeasurement))
                .toList());
    }

    private List<PointDifference> calculatedNeighboringPoints(Map<Integer, ControlPoint> points) {
        Map<Integer, Integer> neighboringPoints = points.keySet()
                                                        .stream()
                                                        .collect(Collectors.toMap(p -> p
                                                                        , p -> getNeighboringPoints(p, points.size())));
        return neighboringPoints.keySet()
                                .stream()
                                .map(p -> mapping(GeodesicPointType.NEIGHBORING, points.get(p)
                                                , points.get(neighboringPoints.get(p))))
                                .toList();
    }

    private Integer getNeighboringPoints(Integer firstPlace, int size) {
        if (firstPlace != size) {
            return ++firstPlace;
        }
       return 1;
    }

    private List<PointDifference> calculatedDiametricalPoints(Map<Integer, ControlPoint> points) {
        int difference = (int) Math.floor(points.keySet().stream().max(Integer::compare).orElse(0) / 2.0);
        if (difference == 0) {
            throw new RuntimeException(
                    String.format("Error in calculating the diametric points, difference=%s", difference)
            );
        }
        Map<Integer, Integer> diametricalPoints = points.keySet().stream()
                                                                .filter(i -> i <= difference)
                                                                .collect(Collectors.toMap(i -> i, i -> i + difference));
        return diametricalPoints.keySet()
                .stream()
                .map(p -> mapping(GeodesicPointType.DIAMETRICAL, points.get(p), points.get(diametricalPoints.get(p))))
                .toList();
    }

    private PointDifference mapping(GeodesicPointType geodesicPointType
                                  , ControlPoint firstPlace
                                  , ControlPoint secondPlace) {
        return mapper.mapToPointDifference(geodesicPointType
                                         , firstPlace.getPlaceNumber()
                                         , secondPlace.getPlaceNumber()
                                         , Math.abs(firstPlace.getDeviation() - secondPlace.getDeviation()));
    }
}