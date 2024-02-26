package ru.nabokovsg.equipment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.standardSize.StandardSizeDto;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mappers.StandardSizeMapper;
import ru.nabokovsg.equipment.repository.StandardSizeRepository;
import ru.nabokovsg.equipment.models.StandardSize;

@Service
@RequiredArgsConstructor
public class StandardSizeServiceImpl implements StandardSizeService {

    private final StandardSizeRepository repository;
    private final StandardSizeMapper mapper;

    @Override
    public StandardSize save(StandardSizeDto standardSizeDto) {
        return repository.save(mapper.mapToStandardSize(standardSizeDto));
    }

    @Override
    public StandardSize update(StandardSizeDto standardSizeDto) {
        if (repository.existsById(standardSizeDto.getId())) {
            return repository.save(mapper.mapToStandardSize(standardSizeDto));
        }
        throw new NotFoundException(
                String.format("StandardSize with id=%s not found for update", standardSizeDto.getId())
        );
    }
}