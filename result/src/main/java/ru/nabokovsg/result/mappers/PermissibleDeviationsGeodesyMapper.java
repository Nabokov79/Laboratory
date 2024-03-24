package ru.nabokovsg.result.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.result.dto.permissibleDeviations.ResponsePermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.dto.permissibleDeviations.PermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;

@Mapper(componentModel = "spring")
public interface PermissibleDeviationsGeodesyMapper {

    PermissibleDeviationsGeodesy mapToPermissibleDeviationsGeodesy(PermissibleDeviationsGeodesyDto geodesyDto);

    ResponsePermissibleDeviationsGeodesyDto mapToFullPermissibleDeviationsGeodesyDto(PermissibleDeviationsGeodesy geodesy);
}