package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.mappers.VisualMeasuringSurveyMapper;
import ru.nabokovsg.result.models.VisualMeasuringSurvey;
import ru.nabokovsg.result.models.builders.SearchParametersBuilder;
import ru.nabokovsg.result.repository.VisualMeasuringSurveyRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VisualMeasuringSurveyServiceImpl implements VisualMeasuringSurveyService {

    private final VisualMeasuringSurveyRepository repository;
    private final VisualMeasuringSurveyMapper mapper;
    private final EquipmentDiagnosedService equipmentDiagnosedService;

    @Override
    public VisualMeasuringSurvey get(Long taskJournalId) {
        return Objects.requireNonNullElseGet(
                repository.findByEquipmentDiagnosedTaskJournalId(taskJournalId)
                , () -> repository.save(
                        mapper.mapToVisualMeasuringSurvey(
                                equipmentDiagnosedService.getEquipmentDiagnosedData(
                                                                new SearchParametersBuilder.SearchParameters()
                                                                                           .taskJournalId(taskJournalId)
                                                                                           .build()))
                )
        );
    }
}