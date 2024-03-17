package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.mappers.PointDifferenceMapper;
import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.PointDifference;
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
        calculatedNeighboringPoints(controlPoints);
        repository.saveAll(calculatedDiametricalPoints(controlPoints));
    }

    private void calculatedNeighboringPoints(List<ControlPoint> controlPoints) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                                                       .collect(Collectors.toMap(ControlPoint::getPlaceNumber, c -> c));
        Map<Integer, Integer> neighboringPoints = identifyNeighboringPoints(points.keySet());
    }

    private List<PointDifference> calculatedDiametricalPoints(List<ControlPoint> controlPoints) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                                                       .collect(Collectors.toMap(ControlPoint::getPlaceNumber, c -> c));
        Map<Integer, Integer> diametricalPoints = identifyDiametricalPoints(points.keySet());
        return diametricalPoints.keySet()
                                .stream()
                                .map(p -> calculatedDifference(points.get(p), points.get(diametricalPoints.get(p))))
                                .toList();
    }

    private Map<Integer, Integer> identifyNeighboringPoints(Set<Integer> placeNumbers) {

    }

    private Map<Integer, Integer> identifyDiametricalPoints(Set<Integer> placeNumbers) {
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

    private PointDifference calculatedDifference(ControlPoint firstPlace, ControlPoint secondPlace) {
        return mapper.mapToPointDifference(firstPlace.getPlaceNumber()
                                         , secondPlace.getPlaceNumber()
                                         , firstPlace.getDeviation() - secondPlace.getDeviation());
    }
}