package ru.nabokovsg.result.services;

import org.springframework.stereotype.Component;
import ru.nabokovsg.result.models.DeviationYear;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CalculatingPointMeasurementServiceImpl implements CalculatingPointMeasurementService {

    @Override
    public Integer getMinMeasurement(List<Integer>  calculatedHeights) {
        Optional<Integer> min = calculatedHeights.stream().min(Integer::compareTo);
        if (min.isEmpty()) {
            return 0;
        } else {
            return min.get();
        }
    }

    @Override
    public Integer getDeviation(Integer min, Integer  calculatedHeight) {
        return min - calculatedHeight;
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