package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.mappers.DeviationYearMapper;
import ru.nabokovsg.result.models.ReferencePoint;
import ru.nabokovsg.result.repository.DeviationYearRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviationYearServiceImpl implements DeviationYearService {

    private final DeviationYearRepository repository;
    private final DeviationYearMapper mapper;

    @Override
    public void save(List<ReferencePoint> points, int year) {
        repository.saveAll(points.stream()
                                 .map(m -> mapper.mapToDeviationYear(year, m.getDeviation(), m))
                                 .toList()
        );
    }

    @Override
    public void update(List<ReferencePoint> points, int year) {
        List<Long> referencePointIds = points.stream().map(ReferencePoint::getId).toList();
        Map<Long, ReferencePoint> referencePoints = points.stream()
                                                           .collect(Collectors.toMap(ReferencePoint::getId, r -> r));
        repository.saveAll(repository.findAllByReferencePointId(referencePointIds)
                .stream()
                .map(d -> {
                    ReferencePoint point = referencePoints.get(d.getReferencePoint().getId());
                    if (year == d.getYear()) {
                        return mapper.mapToUpdateDeviationYear(year, point.getDeviation(), point, d.getId());
                    } else {
                        return d;
                    }
                })
                .toList()
        );
    }
}