package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.result.dto.geodesy.FullPermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.dto.geodesy.PermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.mappers.PermissibleDeviationsGeodesyMapper;
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;
import ru.nabokovsg.result.repository.PermissibleDeviationsGeodesyRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PermissibleDeviationsGeodesyServiceImpl implements  PermissibleDeviationsGeodesyService {

    private final PermissibleDeviationsGeodesyRepository repository;
    private final PermissibleDeviationsGeodesyMapper mapper;

    @Override
    public FullPermissibleDeviationsGeodesyDto save(PermissibleDeviationsGeodesyDto geodesyDto) {
        return mapper.mapToFullPermissibleDeviationsGeodesyDto(
                Objects.requireNonNullElseGet(
                        repository.findByEquipmentTypeIdAndFullAndOld(geodesyDto.getEquipmentTypeId()
                                                                    , geodesyDto.getFull()
                                                                    , geodesyDto.getOld())
                        , () -> repository.save(mapper.mapToPermissibleDeviationsGeodesy(geodesyDto))));
    }

    @Override
    public FullPermissibleDeviationsGeodesyDto update(PermissibleDeviationsGeodesyDto geodesyDto) {
        if (repository.existsById(geodesyDto.getId())) {
            return mapper.mapToFullPermissibleDeviationsGeodesyDto(
                    repository.save(mapper.mapToPermissibleDeviationsGeodesy(geodesyDto))
            );
        }
        throw new NotFoundException(
                String.format("Permissible deviations geodesy with id=%s not found for update", geodesyDto.getId())
        );
    }

    @Override
    public List<FullPermissibleDeviationsGeodesyDto> getAll(Long id) {
        return null;
    }

    @Override
    public PermissibleDeviationsGeodesy getByParameters(Long equipmentTypeId, Boolean full, Boolean old) {
        PermissibleDeviationsGeodesy geodesy = repository.findByEquipmentTypeIdAndFullAndOld(equipmentTypeId
                                                                                           , full
                                                                                           , old);
        if (geodesy == null) {
            throw new NotFoundException(
                    String.format("Permissible deviations geodesy by param:" +
                                " equipmentTypeId=%s, full=%s, old=%s not found", equipmentTypeId, full, old)
            );
        }
        return geodesy;
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(
                String.format("Permissible deviations geodesy with id=%s not found for delete", id)
        );
    }
}