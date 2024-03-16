package ru.nabokovsg.equipment.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.equipment.dto.partElement.ResponsePartElementDto;
import ru.nabokovsg.equipment.dto.partElement.PartElementDto;
import ru.nabokovsg.equipment.dto.partElement.ShortResponsePartElementDto;
import ru.nabokovsg.equipment.models.Element;
import ru.nabokovsg.equipment.models.PartElement;
import ru.nabokovsg.equipment.models.StandardSize;

@Mapper(componentModel = "spring")
public interface PartElementMapper {

    @Mapping(source = "partElementDto.partName", target = "partName")
    @Mapping(source = "element", target = "element")
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(source = "partElementDto.id", target = "id")
    PartElement mapToPartElement(PartElementDto partElementDto, Element element);
    ShortResponsePartElementDto mapToShortPartElementDto(PartElement partElement);
    ResponsePartElementDto mapToFullPartElementDto(PartElement partElement);

    @Mapping(source = "partElement.partName", target = "partName")
    @Mapping(source = "standardSize", target = "standardSize")
    @Mapping(source = "partElement.id", target = "id")
    PartElement mapPartElementWithStandardSize(PartElement partElement, StandardSize standardSize);
}