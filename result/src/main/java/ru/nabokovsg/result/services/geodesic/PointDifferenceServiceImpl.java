package ru.nabokovsg.result.services.geodesic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.mappers.PointDifferenceMapper;
import ru.nabokovsg.result.models.ControlPoint;
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;
import ru.nabokovsg.result.models.PointDifference;
import ru.nabokovsg.result.models.builders.MeasurementBuilder;
import ru.nabokovsg.result.models.enums.GeodesicPointType;
import ru.nabokovsg.result.repository.PointDifferenceRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PointDifferenceServiceImpl implements PointDifferenceService {

    private final PointDifferenceRepository repository;
    private final PointDifferenceMapper mapper;
    private final CalculationGeodesyPointsService calculationGeodesyPointsService;

    @Override
    public void save(MeasurementBuilder builder) {
        repository.saveAll(calculated(builder.getControlPoints())
                                             .stream()
                                             .map(m -> mapper.mapPointDifferenceWithControlPointMeasurement(
                                                    m
                                                  , determinePermissibleDeviation(m, builder.getPermissibleDeviations())
                                                  , builder.getControlPointMeasurement()))
                                            .toList());


    }
    @Override
    public void update(MeasurementBuilder builder) {
        Map<String, Long> pointDifferences = builder.getControlPointMeasurement().getPointDifferences()
                .stream()
                .collect(Collectors.toMap(p -> convertToString(p.getFirstPlaceNumber(), p.getSecondPlaceNumber())
                                        , PointDifference::getId));
        repository.saveAll(calculated(builder.getControlPoints())
                                             .stream()
                                             .map(m -> mapper.mapToUpdatePointDifference(
                                                  m
                                                 , pointDifferences.get(
                                                    convertToString(m.getFirstPlaceNumber(), m.getSecondPlaceNumber())
                                                     )
                                                , determinePermissibleDeviation(m, builder.getPermissibleDeviations())
                                                , builder.getControlPointMeasurement()))
                                            .toList());
    }

    private List<PointDifference> calculated(Set<ControlPoint> controlPoints) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                                                       .collect(Collectors.toMap(ControlPoint::getPlaceNumber, c -> c));
        return Stream.of(calculatedNeighboringPoints(points), calculatedDiametricalPoints(points))
                     .flatMap(Collection::stream)
                     .toList();
    }

    private List<PointDifference> calculatedNeighboringPoints(Map<Integer, ControlPoint> points) {
        Map<Integer, Integer> neighboringPoints = points.keySet()
                                                        .stream()
                                                        .collect(Collectors.toMap(p -> p
                                                                        , p -> getNeighboringPoints(p, points.size())));
        return neighboringPoints.keySet()
                                .stream()
                                .map(p -> mapper.mapToPointDifference(GeodesicPointType.NEIGHBORING
                                                , points.get(p).getPlaceNumber()
                                                , points.get(neighboringPoints.get(p)).getPlaceNumber()
                                                , Math.abs(calculationGeodesyPointsService.getDeviation(
                                                                  points.get(p).getDeviation()
                                                                , points.get(neighboringPoints.get(p)).getDeviation())))
                                )
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
                                .map(p -> mapper.mapToPointDifference(GeodesicPointType.DIAMETRICAL
                                        , points.get(p).getPlaceNumber()
                                        , points.get(diametricalPoints.get(p)).getPlaceNumber()
                                        , Math.abs(calculationGeodesyPointsService.getDeviation(
                                                                  points.get(p).getDeviation()
                                                                , points.get(diametricalPoints.get(p)).getDeviation())))
                                )
                                .toList();
    }

    private Boolean determinePermissibleDeviation(PointDifference pointDifference
                                                         , PermissibleDeviationsGeodesy permissibleDeviationsGeodesy) {
        switch (pointDifference.getGeodesicPointType()) {
            case DIAMETRICAL -> {
                return (pointDifference.getDifference() - permissibleDeviationsGeodesy.getMeasurementError())
                                                      > permissibleDeviationsGeodesy.getMaxDifferenceDiametricPoints();
            }
            case NEIGHBORING -> {
                return (pointDifference.getDifference() - permissibleDeviationsGeodesy.getMeasurementError())
                                                     > permissibleDeviationsGeodesy.getMaxDifferenceNeighboringPoints();
            }
            default -> {
                return false;
            }
        }
    }

    private String convertToString(Integer firstPlaceNumber, Integer secondPlaceNumber) {
        return String.join("", String.valueOf(firstPlaceNumber), String.valueOf(secondPlaceNumber));
    }
}