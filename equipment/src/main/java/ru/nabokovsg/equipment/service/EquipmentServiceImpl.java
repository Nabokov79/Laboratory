package ru.nabokovsg.equipment.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.equipments.EquipmentDto;
import ru.nabokovsg.equipment.dto.equipments.ResponseEquipmentDto;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.models.*;
import ru.nabokovsg.equipment.mappers.EquipmentMapper;
import ru.nabokovsg.equipment.repository.EquipmentRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository repository;
    private final EquipmentMapper mapper;
    private final EntityManager em;
    private final EquipmentTypeService equipmentTypeService;

    @Override
    public ResponseEquipmentDto save(EquipmentDto equipmentDto) {
        return mapper.mapToFullEquipmentDto(
                Objects.requireNonNullElseGet(getByPredicate(equipmentDto),
                        () -> repository.save(
                                        mapper.mapToEquipment(equipmentDto, equipmentTypeService.create(equipmentDto))
                )
             )
        );
    }

    @Override
    public ResponseEquipmentDto update(EquipmentDto equipmentDto) {
        if (repository.existsById(equipmentDto.getId())) {
            return mapper.mapToFullEquipmentDto(
                    repository.save(mapper.mapToEquipment(equipmentDto, equipmentTypeService.create(equipmentDto)))
            );
        }
        throw new NotFoundException(String.format("Equipment with id=%s not found for update", equipmentDto.getId()));
    }

    @Override
    public ResponseEquipmentDto get(Long id) {
        return mapper.mapToFullEquipmentDto(getById(id));
    }

    @Override
    public List<ResponseEquipmentDto> getAll(Long id) {
        return repository.findAllByBuildingId(id).stream()
                                                 .map(mapper::mapToFullEquipmentDto)
                                                 .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Equipment with id=%s not found for delete", id));
    }

    @Override
    public Equipment getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Equipment with id=%s not found", id)));
    }

    private Equipment getByPredicate(EquipmentDto equipmentDto) {
        QEquipment equipment = QEquipment.equipment;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (equipmentDto.getEquipmentName() != null) {
            booleanBuilder.and(equipment.equipmentName.eq(equipmentDto.getEquipmentName()));
        }
        if (equipmentDto.getStationaryNumber() != null) {
            booleanBuilder.and(equipment.stationaryNumber.eq(equipmentDto.getStationaryNumber()));
        }
        if (equipmentDto.getVolume() != null) {
            booleanBuilder.and(equipment.volume.eq(equipmentDto.getVolume()));
        }
        if (equipmentDto.getModel() != null) {
            booleanBuilder.and(equipment.model.eq(equipmentDto.getModel()));
        }
        return new JPAQueryFactory(em).from(equipment)
                                      .select(equipment)
                                      .where(booleanBuilder)
                                      .fetchOne();
    }
}