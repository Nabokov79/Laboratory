package ru.nabokovsg.company.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.heatSupplyArea.HeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ShortHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.FullHeatSupplyAreaDto;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mappers.HeatSupplyAreaMapper;
import ru.nabokovsg.company.models.HeatSupplyArea;
import ru.nabokovsg.company.repository.HeatSupplyAreaRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HeatSupplyAreaServiceImpl implements HeatSupplyAreaService {

    private final HeatSupplyAreaRepository repository;
    private final HeatSupplyAreaMapper mapper;

    @Override
    public ShortHeatSupplyAreaDto save(HeatSupplyAreaDto areaDto) {
        return mapper.mapToShortHeatSupplyAreaDto(
                Objects.requireNonNullElseGet(repository.findByFullName(areaDto.getFullName())
                        , () -> repository.save(mapper.mapToHeatSupplyArea(areaDto)))
        );
    }

    @Override
    public ShortHeatSupplyAreaDto update(HeatSupplyAreaDto areaDto) {
        if (repository.existsById(areaDto.getId())) {
            return mapper.mapToShortHeatSupplyAreaDto(
                    repository.save(
                            mapper.mapToHeatSupplyArea(areaDto))
            );
        }
        throw new NotFoundException(
                String.format("HeatSupplyArea with name=%s not found for update.", areaDto.getFullName()));
    }

    @Override
    public FullHeatSupplyAreaDto get(Long id) {
        return mapper.mapToFullHeatSupplyAreaDto(getById(id));
    }

    @Override
    public HeatSupplyArea getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("HeatSupplyArea with id=%s not found.", id)));
    }

    @Override
    public List<ShortHeatSupplyAreaDto> getAll(Long branchId) {
        return repository.findAllByBranchId(branchId)
                         .stream()
                         .map(mapper::mapToShortHeatSupplyAreaDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("HeatSupplyArea with id=%s not found for delete.", id));
    }
}