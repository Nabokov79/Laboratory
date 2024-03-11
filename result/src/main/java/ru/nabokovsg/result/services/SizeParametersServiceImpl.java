package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.nabokovsg.result.dto.sizeParameters.SizeParametersDto;
import ru.nabokovsg.result.mappers.SizeParametersMapper;
import ru.nabokovsg.result.models.SizeParameters;
import ru.nabokovsg.result.repository.SizeParametersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SizeParametersServiceImpl implements SizeParametersService {

    private final SizeParametersRepository repository;
    private final SizeParametersMapper mapper;

    @Override
    public List<SizeParameters> save(List<SizeParametersDto> parametersDto) {
        parametersDto = parametersDto.stream().distinct().collect(Collectors.toList());
        Map<String, SizeParameters> parameters = repository.findAllDefectParameterByParametersName(
                                                                 (parametersDto.stream()
                                                                          .map(SizeParametersDto::getParametersName)
                                                                          .distinct()
                                                                          .toList())
                                                        )
                                        .stream().collect(Collectors.toMap(SizeParameters::getParametersName, d -> d));
        if (parameters.isEmpty()) {
            return repository.saveAll(parametersDto.stream()
                                                    .map(mapper::mapToSizeParameters)
                                                    .toList());
        } else {
            parametersDto = parametersDto.stream().filter(p -> !parameters.containsKey(p.getParametersName())).toList();
            if (!parametersDto.isEmpty()) {
                List<SizeParameters> parametersDb = repository.saveAll(parametersDto.stream()
                                                                                     .map(mapper::mapToSizeParameters)
                                                                                     .toList());
                for (SizeParameters parameter : parametersDb) {
                    parameters.put(parameter.getParametersName(), parameter);
                }
            }
            return parameters.values().stream().toList();
        }
    }

    @Override
    public List<SizeParameters> update(List<SizeParametersDto> parametersDto) {
        validateIds(parametersDto.stream().map(SizeParametersDto::getId).distinct().toList());
            return repository.saveAll(parametersDto.stream()
                                                    .map(mapper::mapToSizeParameters)
                                                    .toList());
    }

    private void validateIds(List<Long> ids) {
        Map<Long, SizeParameters> parameters = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SizeParameters::getId, d -> d));
        if (parameters.size() != ids.size() || parameters.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(parameters.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new ChangeSetPersister.NotFoundException(String.format("parameters with ids= %s not found", ids));
        }
    }
}