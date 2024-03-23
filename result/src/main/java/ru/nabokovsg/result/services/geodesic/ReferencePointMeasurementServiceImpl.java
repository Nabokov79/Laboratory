package ru.nabokovsg.result.services.geodesic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.referencePoint.ReferencePointDto;
import ru.nabokovsg.result.mappers.ReferencePointMapper;
import ru.nabokovsg.result.models.*;
import ru.nabokovsg.result.models.builders.MeasurementBuilder;
import ru.nabokovsg.result.repository.ReferencePointRepository;
import ru.nabokovsg.result.services.DeviationYearService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReferencePointMeasurementServiceImpl implements ReferencePointMeasurementService {

    private final ReferencePointRepository repository;
    private final ReferencePointMapper mapper;
    private final DeviationYearService deviationYearService;
    private final CalculationGeodesyPointsService calculatingPointService;

    @Override
    public void save(MeasurementBuilder builder) {
        Integer min = calculatingPointService.getMinMeasurement(builder.getGeodesicMeasurements()
                                                                       .stream()
                                                                       .map(GeodesicMeasurement::getReferencePointValue)
                                                                       .toList());
        int year = LocalDate.now().getYear();
        List<ReferencePoint> points = repository.saveAll(builder.getGeodesicMeasurements()
                                                                .stream()
                                                                .filter(m -> m.getReferencePointValue() != null)
                                                                .map(m -> getReferencePoint(m
                                                                        , builder.getEquipmentDiagnosed()
                                                                        , min
                                                                        , year))
                                                                .toList());
        deviationYearService.save(points, year);
    }

    @Override
    public void update(MeasurementBuilder builder) {
        Integer min = calculatingPointService.getMinMeasurement(builder.getGeodesicMeasurements()
                                                                       .stream()
                                                                       .map(GeodesicMeasurement::getReferencePointValue)
                                                                       .toList());
        int year = LocalDate.now().getYear();
        Map<Integer, ReferencePoint> referencePoints = getReferencePoints(builder.getEquipmentDiagnosed().getId())
                .stream()
                .collect(Collectors.toMap(ReferencePoint::getPlaceNumber, r -> r));
        List<ReferencePoint> points =
                               repository.saveAll(builder.getGeodesicMeasurements().stream()
                                       .map(m -> {
                                           ReferencePoint point = referencePoints.get(m.getNumberMeasurementLocation());
                                           return mapper.mapToUpdateReferencePoint(point.getId()
                                                   , point.getEquipmentDiagnosed()
                                                   , point.getPlaceNumber()
                                                   , m.getReferencePointValue());
                                       })
                                       .map(m -> {
                                           Integer deviation = calculatingPointService.getDeviation(min
                                                                                             , m.getCalculatedHeight());
                                           Integer precipitation = calculatingPointService.getPrecipitation(deviation
                                                   , year
                                                   , m.getDeviationYeas());
                                           return mapper.mapToReferencePointData(m, deviation, precipitation);
                                       })
                                      .toList());
        deviationYearService.update(points, year);
    }

    @Override
    public List<ReferencePointDto> getAll(Long id) {
        return getReferencePoints(id).stream()
                                     .map(mapper::mapToReferencePointDto)
                                     .toList();
    }

    private Set<ReferencePoint> getReferencePoints(Long equipmentDiagnosedId) {
        return repository.getAllByEquipmentDiagnosedId(equipmentDiagnosedId);
    }

    private ReferencePoint getReferencePoint(GeodesicMeasurement measurement, EquipmentDiagnosed equipmentDiagnosed, Integer min, int year) {
        ReferencePoint point = mapper.mapToReferencePoint(equipmentDiagnosed
                                                        , measurement.getNumberMeasurementLocation()
                                                        , measurement.getReferencePointValue());
        Integer deviation = calculatingPointService.getDeviation(min, point.getCalculatedHeight());
        Integer precipitation = calculatingPointService.getPrecipitation(deviation, year, point.getDeviationYeas());
        return mapper.mapToReferencePointData(point, deviation, precipitation);
    }
}