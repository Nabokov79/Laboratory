package ru.nabokovsg.result.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.result.dto.permissibleDeviations.ResponsePermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.dto.permissibleDeviations.PermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.mappers.PermissibleDeviationsGeodesyMapper;
import ru.nabokovsg.result.models.EquipmentDiagnosed;
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
    public ResponsePermissibleDeviationsGeodesyDto save(PermissibleDeviationsGeodesyDto geodesyDto) {
        return mapper.mapToFullPermissibleDeviationsGeodesyDto(
                Objects.requireNonNullElseGet(
                        repository.findByEquipmentTypeIdAndFullAndOld(geodesyDto.getEquipmentTypeId()
                                                                    , geodesyDto.getFull()
                                                                    , geodesyDto.getOld())
                        , () -> repository.save(mapper.mapToPermissibleDeviationsGeodesy(geodesyDto))));
    }

    @Override
    public ResponsePermissibleDeviationsGeodesyDto update(PermissibleDeviationsGeodesyDto geodesyDto) {
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
    public List<ResponsePermissibleDeviationsGeodesyDto> getAll(Long id) {
        return null;
    }

    @Override
    public PermissibleDeviationsGeodesy getByParameters(EquipmentDiagnosed equipment) {
        PermissibleDeviationsGeodesy geodesy = repository.findByEquipmentTypeIdAndFullAndOld(equipment.getEquipmentTypeId()
                                                                                           , equipment.getFull()
                                                                                           , equipment.getEquipmentOld());
        if (geodesy == null) {
            throw new NotFoundException(
                    String.format("Permissible deviations geodesy by param:" +
                                " equipmentTypeId=%s, full=%s, old=%s not found", equipment.getEquipmentTypeId()
                                                                                , equipment.getFull()
                                                                                , equipment.getEquipmentOld())
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