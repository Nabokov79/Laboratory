package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.mappers.PointDifferenceMapper;
import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.PointDifference;
import ru.nabokovsg.result.models.enums.GeodesicPointType;
import ru.nabokovsg.result.repository.PointDifferenceRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointDifferenceServiceImpl implements PointDifferenceService {

    private final PointDifferenceRepository repository;
    private final PointDifferenceMapper mapper;

    @Override
    public void save(List<ControlPoint> controlPoints) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                .collect(Collectors.toMap(ControlPoint::getPlaceNumber, c -> c));
        repository.saveAll(calculatedNeighboringPoints(points));
        repository.saveAll(calculatedDiametricalPoints(points));
    }

    @Override
    public void update(List<ControlPoint> controlPoints) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                .collect(Collectors.toMap(ControlPoint::getPlaceNumber, c -> c));
        repository.saveAll(calculatedNeighboringPoints(points));
        repository.saveAll(calculatedDiametricalPoints(points));
    }

    private List<PointDifference> calculatedNeighboringPoints(Map<Integer, ControlPoint> points) {
        int size = points.size();
        Map<Integer, Integer> neighboringPoints = points.keySet()
                                                 .stream()
                                                 .collect(Collectors.toMap(p -> p, p -> getNeighboringPoints(p, size)));
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
        Map<Integer, Integer> diametricalPoints = getDiametricalPoints(points.keySet());
        return diametricalPoints.keySet()
                .stream()
                .map(p -> mapping(GeodesicPointType.DIAMETRICAL, points.get(p), points.get(diametricalPoints.get(p))))
                .toList();
    }

    private Map<Integer, Integer> getDiametricalPoints(Set<Integer> placeNumbers) {
        int difference = (int) Math.floor(placeNumbers.stream().max(Integer::compare).orElse(0) / 2.0);
        if (difference == 0) {
            throw new RuntimeException(
                    String.format("Error in calculating the diametric points, difference=%s", difference)
            );
        }
        return placeNumbers.stream()
                .filter(i -> i <= difference)
                .collect(Collectors.toMap(i -> i, i -> i + difference));
    }

    private PointDifference mapping(GeodesicPointType geodesicPointType
                                  , ControlPoint firstPlace
                                  , ControlPoint secondPlace) {
        return mapper.mapToPointDifference(geodesicPointType
                                         , firstPlace.getPlaceNumber()
                                         , secondPlace.getPlaceNumber()
                                         , firstPlace.getDeviation() - secondPlace.getDeviation());
    }
}