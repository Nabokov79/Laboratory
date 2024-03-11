package ru.nabokovsg.template.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.template.client.LNKClient;
import ru.nabokovsg.template.mappers.MeasuringToolMapper;
import ru.nabokovsg.template.models.MeasuringToolTemplate;
import ru.nabokovsg.template.repository.MeasuringToolTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasuringToolTemplateServiceImpl implements MeasuringToolTemplateService {

    private final MeasuringToolTemplateRepository repository;
    private final MeasuringToolMapper mapper;
    private final LNKClient client;

    @Override
    public List<MeasuringToolTemplate> save(List<Long> ids) {
        return repository.saveAll(
                client.getMeasuringTool(ids)
                      .stream()
                      .map(mapper::mapToMeasuringToolTemplate)
                      .toList()
        );
    }
}