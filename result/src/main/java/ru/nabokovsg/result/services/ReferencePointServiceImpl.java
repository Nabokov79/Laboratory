package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.mappers.ReferencePointMapper;
import ru.nabokovsg.result.models.DeviationYear;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
import ru.nabokovsg.result.models.GeodesicMeasurement;
import ru.nabokovsg.result.models.ReferencePoint;
import ru.nabokovsg.result.repository.ReferencePointRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReferencePointServiceImpl implements ReferencePointService {

    private final ReferencePointRepository repository;
    private final ReferencePointMapper mapper;
    private final DeviationYearService deviationYearService;

    @Override
    public void save(EquipmentDiagnosed equipmentDiagnosed, List<GeodesicMeasurement> measurements) {
        Integer min = getMinMeasurement(measurements);
        int year = LocalDate.now().getYear();
        Map<Integer, List<DeviationYear>> referencePoints = repository.getAlByEquipmentDiagnosedId(equipmentDiagnosed.getId())
                 .stream()
                 .collect(Collectors.toMap(ReferencePoint::getPlaceNumber, ReferencePoint::getDeviationYeas));
        List<ReferencePoint> points = repository.saveAll(
                measurements.stream()
                            .map(m -> mapper.mapToReferencePoint(equipmentDiagnosed
                                                               , m.getNumberMeasurementLocation()
                                                               , m.getReferencePointValue()))
                            .map(m -> {
                                Integer deviation = sum(min, m.getCalculatedHeight());
                                Integer precipitation = getPrecipitation(deviation
                                                                       , year
                                                                       , referencePoints.get(m.getPlaceNumber()));
                                return mapper.mapToReferencePointData(m, deviation, precipitation);
                            })
                            .toList());
        deviationYearService.save(points, year);
    }

    @Override
    public void update(EquipmentDiagnosed equipmentDiagnosed, List<GeodesicMeasurement> measurements) {
        Integer min = getMinMeasurement(measurements);
        int year = LocalDate.now().getYear();
        Map<Integer, ReferencePoint> referencePoints = repository.getAlByEquipmentDiagnosedId(equipmentDiagnosed.getId())
                .stream()
                .collect(Collectors.toMap(ReferencePoint::getPlaceNumber, r -> r));
        List<ReferencePoint> points =
                               repository.saveAll(measurements.stream()
                                       .map(m -> {
                                           ReferencePoint point = referencePoints.get(m.getNumberMeasurementLocation());
                                           return mapper.mapToUpdateReferencePoint(point.getId()
                                                   , point.getEquipmentDiagnosed()
                                                   , point.getPlaceNumber()
                                                   , m.getReferencePointValue());
                                       })
                                       .map(m -> {
                                           Integer deviation = sum(min, m.getCalculatedHeight());
                                           Integer precipitation = getPrecipitation(deviation
                                                   , year
                                                   , m.getDeviationYeas());
                                           return mapper.mapToReferencePointData(m, deviation, precipitation);
                                       })
                                      .toList());
        deviationYearService.update(points, year);
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

    private Integer getPrecipitation(Integer newDeviation, int year, List<DeviationYear> deviationYeas) {
       if (deviationYeas == null) {
           return 0;
       }
        Map<Integer, Integer> deviations = deviationYeas.stream()
                .collect(Collectors.toMap(DeviationYear::getYear
                        , DeviationYear::getDeviation));
        return newDeviation - deviations.get(year);
    }
}