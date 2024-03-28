package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.parameters.ParameterMeasurementDto;
import ru.nabokovsg.result.mappers.ParameterMeasurementMapper;
import ru.nabokovsg.result.models.ParameterMeasurement;
import ru.nabokovsg.result.models.Parameters;
import ru.nabokovsg.result.models.builders.ParameterMeasurementBuilder;
import ru.nabokovsg.result.models.enums.ActionsOnParameter;
import ru.nabokovsg.result.repository.ParameterMeasurementRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParameterMeasurementServiceImpl implements ParameterMeasurementService {

    private final ParameterMeasurementRepository repository;
    private final ParameterMeasurementMapper mapper;

    @Override
    public Set<ParameterMeasurement> save(ParameterMeasurementBuilder builder) {
        Map<Long, Parameters> parameters = builder.getDefectParameters()
                                                  .stream()
                                                  .collect(Collectors.toMap(Parameters::getId, p -> p));
        Map<String, ParameterMeasurement> parametersMeasurementsDb = builder.getDefectMeasurement()
                                            .getParametersMeasurements()
                                            .stream()
                                            .collect(Collectors.toMap(ParameterMeasurement::getParameterName, p -> p));
        return new HashSet<>(repository.saveAll(
                 builder.getParametersMeasurements()
                        .stream()
                        .map(p -> router(p
                                   , parametersMeasurementsDb.get(parameters.get(p.getParameterId()).getParameterName())
                                   , parameters.get(p.getParameterId()).getActionsOnParameter()))
                        .toList()));
    }

    @Override
    public Set<ParameterMeasurement> update(ParameterMeasurementBuilder builder) {
        return null;
    }

    private ParameterMeasurement router(ParameterMeasurementDto parameterDto, ParameterMeasurement parameter
                                                                            , ActionsOnParameter actionsOnParameter) {
        switch (actionsOnParameter) {
            case MIN -> countMin(parameterDto, parameter);
            case MAX -> countMax(parameterDto, parameter);
            case MAX_MIN -> countMinAndMax(parameterDto, parameter);
            case QUANTITY -> countQuantity(parameter);
            default -> {return  mapper.mapToParameterMeasurement(parameterDto);}
        }
        return parameter;
    }

    private void countMin(ParameterMeasurementDto parameterDto, ParameterMeasurement parameter) {
        if (parameter == null) {
            parameter = new ParameterMeasurement();
            parameter.setMinValue(parameterDto.getParameterValue());
            return;
        }
        if (parameterDto.getParameterValue() < parameter.getMinValue()) {
            parameter.setMinValue(parameterDto.getParameterValue());
        }
    }

    private void countMax(ParameterMeasurementDto parameterDto, ParameterMeasurement parameter) {
        if (parameter == null) {
            parameter = new ParameterMeasurement();
            parameter.setMaxValue(parameterDto.getParameterValue());
            return;
        }
        if (parameter.getMinValue() < parameterDto.getParameterValue() && parameter.getMaxValue() != null) {
            parameter.setMaxValue(parameterDto.getParameterValue());
        }
    }

    private void countMinAndMax(ParameterMeasurementDto parameterDto, ParameterMeasurement parameter) {
        if (parameter == null) {
            parameter = new ParameterMeasurement();
            parameter.setMinValue(parameterDto.getParameterValue());
            parameter.setMaxValue(parameterDto.getParameterValue());
            return;
        }
        countMin(parameterDto, parameter);
        countMax(parameterDto, parameter);
    }

    private void countQuantity(ParameterMeasurement parameter) {
        parameter.setParameterValue(parameter.getParameterValue() + 1);
    }
}