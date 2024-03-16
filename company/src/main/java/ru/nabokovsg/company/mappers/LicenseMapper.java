package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.company.dto.licenses.ResponseLicenseDto;
import ru.nabokovsg.company.dto.licenses.LicenseDto;
import ru.nabokovsg.company.models.Licenses;
import ru.nabokovsg.company.models.Organization;

@Mapper(componentModel = "spring")
public interface LicenseMapper {

    @Mapping(target = "id", ignore = true)
    Licenses mapToNewLicenses(LicenseDto licenseDto, Organization issuedLicense);

    @Mapping(source = "licenseDto.id", target = "id")
    Licenses mapToUpdateLicenses(LicenseDto licenseDto, Organization issuedLicense);

    ResponseLicenseDto mapToFullLicenseDto(Licenses license);
}