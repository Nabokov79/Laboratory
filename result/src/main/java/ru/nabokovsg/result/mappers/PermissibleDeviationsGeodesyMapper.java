package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.result.dto.geodesy.FullPermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.dto.geodesy.PermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;

@Mapper(componentModel = "spring")
public interface PermissibleDeviationsGeodesyMapper {

    PermissibleDeviationsGeodesy mapToPermissibleDeviationsGeodesy(PermissibleDeviationsGeodesyDto geodesyDto);

    FullPermissibleDeviationsGeodesyDto mapToFullPermissibleDeviationsGeodesyDto(PermissibleDeviationsGeodesy geodesy);
}