package ru.nabokovsg.equipment.service;

import ru.nabokovsg.equipment.dto.element.ElementDto;
import ru.nabokovsg.equipment.dto.element.ResponseElementDto;
import ru.nabokovsg.equipment.dto.element.ShortResponseElementDto;
import ru.nabokovsg.equipment.models.Element;
import ru.nabokovsg.equipment.models.PartElement;

import java.util.List;

public interface ElementService {

    ShortResponseElementDto save(ElementDto elementDto);

    ShortResponseElementDto update(ElementDto elementDto);

    ResponseElementDto get(Long id);

    List<ShortResponseElementDto> getAll(Long id);

    Element getById(Long id);
    void addPartElement(Long id, PartElement partElement);

   void delete(Long id);
}