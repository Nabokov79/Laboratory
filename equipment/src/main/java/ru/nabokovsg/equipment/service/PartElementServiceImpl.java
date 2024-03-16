package ru.nabokovsg.equipment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.partElement.ResponsePartElementDto;
import ru.nabokovsg.equipment.dto.partElement.PartElementDto;
import ru.nabokovsg.equipment.dto.partElement.ShortResponsePartElementDto;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mappers.PartElementMapper;
import ru.nabokovsg.equipment.models.PartElement;
import ru.nabokovsg.equipment.repository.PartElementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartElementServiceImpl implements PartElementService {

    private final PartElementRepository repository;
    private final PartElementMapper mapper;
    private final ElementService elementService;
    private final StandardSizeService standardSizeService;

    @Override
    public ResponsePartElementDto save(PartElementDto partElementDto) {
        PartElement partElement = repository.findByElementIdAndPartName(partElementDto.getElementId()
                , partElementDto.getPartName());
        if (partElement == null) {
            partElement = mapper.mapToPartElement(partElementDto
                    , elementService.getById(partElementDto.getElementId()));
            if (partElementDto.getStandardSize() != null) {
                partElement = mapper.mapPartElementWithStandardSize(partElement
                                            , standardSizeService.save(partElementDto.getStandardSize()));
            }
            partElement = repository.save(partElement);
            elementService.addPartElement(partElementDto.getElementId(), partElement);
        }
        return mapper.mapToFullPartElementDto(partElement);
    }

    @Override
    public ResponsePartElementDto update(PartElementDto partElementDto) {
        if (repository.existsById(partElementDto.getId())) {
            PartElement partElement = mapper.mapToPartElement(partElementDto
                    , elementService.getById(partElementDto.getElementId()));
            if (partElementDto.getStandardSize() != null) {
                partElement = mapper.mapPartElementWithStandardSize(partElement
                                            , standardSizeService.update(partElementDto.getStandardSize()));
            }
            return mapper.mapToFullPartElementDto(repository.save(partElement));
        }
        throw new NotFoundException(
                String.format("PartElement with id=%s not found for update", partElementDto.getId())
        );
    }

    @Override
    public ResponsePartElementDto get(Long id) {
        return mapper.mapToFullPartElementDto(
                repository.findById(id)
                        .orElseThrow(() -> new NotFoundException(String.format("PartElement with id=%s not found", id)))
        );
    }

    @Override
    public List<ShortResponsePartElementDto> getAll(Long id) {
        return repository.findAllByElementId(id)
                         .stream()
                         .map(mapper::mapToShortPartElementDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("PartElement with id=%s not found for delete", id));
    }
}