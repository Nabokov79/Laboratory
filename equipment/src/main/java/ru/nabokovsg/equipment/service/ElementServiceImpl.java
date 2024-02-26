package ru.nabokovsg.equipment.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.element.ElementDto;
import ru.nabokovsg.equipment.dto.element.FullElementDto;
import ru.nabokovsg.equipment.dto.element.ShortElementDto;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mappers.ElementMapper;
import ru.nabokovsg.equipment.models.Element;
import ru.nabokovsg.equipment.models.PartElement;
import ru.nabokovsg.equipment.models.QElement;
import ru.nabokovsg.equipment.models.QEquipment;
import ru.nabokovsg.equipment.repository.ElementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElementServiceImpl implements ElementService {

    private final ElementRepository repository;
    private final ElementMapper mapper;
    private final EntityManager em;
    private final EquipmentService equipmentService;
    private final StandardSizeService standardSizeService;

    @Override
    public ShortElementDto save(ElementDto elementDto) {
        Element element = getByPredicate(elementDto);
        if (element == null) {
            element = mapper.mapToElement(elementDto, equipmentService.getById(elementDto.getEquipmentId()));
            if (elementDto.getStandardSize() != null) {
                element = mapper.mapElementWithStandardSize(element
                                                         , standardSizeService.save(elementDto.getStandardSize()));
            }
            element = repository.save(element);
        }
        return mapper.mapToShortElementDto(element);
    }

    @Override
    public ShortElementDto update(ElementDto elementDto) {
        if (repository.existsById(elementDto.getId())) {
            return mapper.mapToShortElementDto(
                    repository.save(mapper.mapToElement(elementDto
                                                    , equipmentService.getById(elementDto.getEquipmentId())))
            );
        }
        throw new NotFoundException(String.format("Element with id=%s not found for update", elementDto.getId()));
    }

    @Override
    public FullElementDto get(Long id) {
        return mapper.mapToFullElementDto(getById(id));
    }

    @Override
    public List<ShortElementDto> getAll(Long id) {
        return repository.findAllByEquipmentId(id)
                         .stream()
                         .map(mapper::mapToShortElementDto)
                         .toList();
    }

    @Override
    public void addPartElement(Long id, PartElement partElement) {
        Element element = getById(id);
        element.getPartsElement().add(partElement);
        repository.save(element);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Element with id=%s not found for delete", id));
    }

    @Override
    public Element getById(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new NotFoundException(String.format("Element with id=%s not found", id)));
    }

    private Element getByPredicate(ElementDto elementDto) {
        QElement element = QElement.element;
        QEquipment equipment = QEquipment.equipment;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(element.elementName.eq(elementDto.getElementName()));
        booleanBuilder.and(equipment.id.eq(elementDto.getEquipmentId()));
        return new JPAQueryFactory(em).from(element)
                .select(element)
                .where(booleanBuilder)
                .fetchOne();
    }
}