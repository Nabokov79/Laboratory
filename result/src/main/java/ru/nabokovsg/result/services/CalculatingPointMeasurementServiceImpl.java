package ru.nabokovsg.result.services;

import org.springframework.stereotype.Component;
import ru.nabokovsg.result.models.DeviationYear;
import ru.nabokovsg.result.models.GeodesicMeasurement;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CalculatingPointMeasurementServiceImpl implements CalculatingPointMeasurementService {

    @Override
    public Integer getMinMeasurement(List<GeodesicMeasurement> measurements) {
        Integer min = 0;
        for (GeodesicMeasurement measurement: measurements) {
            if (min == 0 || measurement.getControlPointValue() < min) {
                min = measurement.getControlPointValue();
            }
        }
        return min;
    }

    @Override
    public Integer getDeviation(Integer min, Integer controlPointValue) {
        return min - controlPointValue;
    }

    @Override
    public Integer getPrecipitation(Integer newDeviation, int year, List<DeviationYear> deviationYeas) {
        if (deviationYeas == null) {
            return 0;
        }
        Map<Integer, Integer> deviations = deviationYeas.stream()
                .collect(Collectors.toMap(DeviationYear::getYear
                        , DeviationYear::getDeviation));
        return newDeviation - deviations.get(year);
    }
}