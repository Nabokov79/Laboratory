package ru.nabokovsg.equipment.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.equipment.dto.element.ElementDto;
import ru.nabokovsg.equipment.dto.element.FullElementDto;
import ru.nabokovsg.equipment.dto.element.ShortElementDto;
import ru.nabokovsg.equipment.models.Element;
import ru.nabokovsg.equipment.models.Equipment;
import ru.nabokovsg.equipment.models.StandardSize;

@Mapper(componentModel = "spring")
public interface ElementMapper {

    @Mapping(source = "elementDto.elementName", target = "elementName")
    @Mapping(source = "equipment", target = "equipment")
    @Mapping(source = "elementDto.id", target = "id")
    Element mapToElement(ElementDto elementDto, Equipment equipment);

    ShortElementDto mapToShortElementDto(Element element);

    FullElementDto mapToFullElementDto(Element element);

    @Mapping(source = "element.elementName", target = "elementName")
    @Mapping(source = "standardSize", target = "standardSize")
    @Mapping(source = "element.id", target = "id")
    Element mapElementWithStandardSize(Element element, StandardSize standardSize);
}