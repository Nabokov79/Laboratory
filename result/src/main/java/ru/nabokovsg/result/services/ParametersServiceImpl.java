package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.result.dto.parameters.ParametersDto;
import ru.nabokovsg.result.mappers.ParametersMapper;
import ru.nabokovsg.result.models.Parameters;
import ru.nabokovsg.result.models.enums.ActionsOnParameter;
import ru.nabokovsg.result.repository.ParametersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParametersServiceImpl implements ParametersService {

    private final ParametersRepository repository;
    private final ParametersMapper mapper;

    @Override
    public List<Parameters> save(List<ParametersDto> parametersDto) {
        Map<String, Parameters> parameters = repository.findAllDefectParameterByParametersName(
                                                                 (parametersDto.stream()
                                                                               .map(ParametersDto::getParameterName)
                                                                               .distinct()
                                                                               .toList()))
                                                       .stream()
                                                       .collect(Collectors.toMap(Parameters::getParameterName, d -> d));
        parametersDto.stream()
                .map(m -> mapper.mapToParameters(m, getActionsOnParameter(m)))
                .toList()
                .forEach(p -> parameters.putIfAbsent(p.getParameterName(), p));
            return repository.saveAll(parameters.values());
    }

    @Override
    public List<Parameters> update(List<ParametersDto> parametersDto) {
        validateIds(parametersDto.stream()
                                 .map(ParametersDto::getId)
                                 .distinct()
                                 .toList());
            return repository.saveAll(parametersDto.stream()
                                                    .map(m -> mapper.mapToParameters(m, getActionsOnParameter(m)))
                                                    .toList());
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Parameters> parameters = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Parameters::getId, d -> d));
        if (parameters.size() != ids.size() || parameters.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(parameters.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("parameters with ids= %s not found", ids));
        }
    }

    private ActionsOnParameter getActionsOnParameter(ParametersDto parameter) {
        if (parameter.isMax()) {
            return ActionsOnParameter.MAX;
        }
        if (parameter.isMin()) {
            return ActionsOnParameter.MIN;
        }
        if (parameter.isMaxAndMin()) {
            return ActionsOnParameter.MAX_MIN;
        }
        if (parameter.isQuantity()) {
            return ActionsOnParameter.QUANTITY;
        }
        return ActionsOnParameter.NO_ACTION;
    }
}