package ru.nabokovsg.equipment.service;

import ru.nabokovsg.equipment.dto.element.ElementDto;
import ru.nabokovsg.equipment.dto.element.FullElementDto;
import ru.nabokovsg.equipment.dto.element.ShortElementDto;
import ru.nabokovsg.equipment.models.Element;
import ru.nabokovsg.equipment.models.PartElement;

import java.util.List;

public interface ElementService {

    ShortElementDto save(ElementDto elementDto);

    ShortElementDto update(ElementDto elementDto);

    FullElementDto get(Long id);

    List<ShortElementDto> getAll(Long id);

    Element getById(Long id);
    void addPartElement(Long id, PartElement partElement);

   void delete(Long id);
}