package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.models.GeodesicMeasurement;
import ru.nabokovsg.result.repository.ReferencePointRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReferencePointServiceImpl implements ReferencePointService {

    private final ReferencePointRepository repository;

    @Override
    public void save(List<GeodesicMeasurement> measurements) {

    }
}