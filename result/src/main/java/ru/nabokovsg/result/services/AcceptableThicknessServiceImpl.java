package ru.nabokovsg.result.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.result.dto.acceptable.AcceptableThicknessDto;
import ru.nabokovsg.result.dto.acceptable.FullAcceptableThicknessDto;
import ru.nabokovsg.result.mappers.AcceptableThicknessMapper;
import ru.nabokovsg.result.models.AcceptableThickness;
import ru.nabokovsg.result.models.QAcceptableThickness;
import ru.nabokovsg.result.repository.AcceptableThicknessRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AcceptableThicknessServiceImpl implements AcceptableThicknessService {

    private final AcceptableThicknessRepository repository;
    private final AcceptableThicknessMapper mapper;
    private final EntityManager em;
    @Override
    public FullAcceptableThicknessDto save(AcceptableThicknessDto thicknessDto) {
        return mapper.mapToFullAcceptableThicknessDto(
                Objects.requireNonNullElseGet(getByPredicate(thicknessDto)
                        , () -> repository.save(mapper.mapToAcceptableThickness(thicknessDto))));
    }

    @Override
    public FullAcceptableThicknessDto update(AcceptableThicknessDto thicknessDto) {
        if (repository.existsById(thicknessDto.getId())) {
            return mapper.mapToFullAcceptableThicknessDto(
                    repository.save(mapper.mapToAcceptableThickness(thicknessDto))
            );
        }
        throw new NotFoundException(
                String.format("AcceptableThickness with id=%s not found for update", thicknessDto.getId())
        );
    }

    @Override
    public List<FullAcceptableThicknessDto> getAll(Long id) {
        return repository.findAllByEquipmentTypeId(id)
                         .stream()
                         .map(mapper::mapToFullAcceptableThicknessDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("AcceptableThickness with id=%s not found for delete", id));
    }

    private AcceptableThickness getByPredicate(AcceptableThicknessDto thicknessDto) {
        QAcceptableThickness thickness = QAcceptableThickness.acceptableThickness;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (thicknessDto.getEquipmentTypeId() != null) {
            booleanBuilder.and(thickness.equipmentTypeId.eq(thicknessDto.getEquipmentTypeId()));
        }
        if (thicknessDto.getElementId() != null) {
            booleanBuilder.and(thickness.elementId.eq(thicknessDto.getElementId()));
        }
        if (thicknessDto.getPartElementId() != null) {
            booleanBuilder.and(thickness.partElementId.eq(thicknessDto.getPartElementId()));
        }
        if (thicknessDto.getDiameter() != null) {
            booleanBuilder.and(thickness.diameter.eq(thicknessDto.getDiameter()));
        }
        return new JPAQueryFactory(em).from(thickness)
                .select(thickness)
                .where(booleanBuilder)
                .fetchOne();
    }
}