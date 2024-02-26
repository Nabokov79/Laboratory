package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.company.dto.organization.FullOrganizationDto;
import ru.nabokovsg.company.dto.organization.OrganizationDto;
import ru.nabokovsg.company.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.company.models.Address;
import ru.nabokovsg.company.models.Organization;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    @Mapping(source = "address", target = "address")
    @Mapping(target = "id", ignore = true)
    Organization mapToNewOrganization(OrganizationDto organizationDto, Address address);

    @Mapping(source = "address", target = "address")
    @Mapping(source = "organizationDto.id", target = "id")
    Organization mapToUpdateOrganization(OrganizationDto organizationDto, Address address);

    FullOrganizationDto mapToOrganizationDto(Organization organization);

    ShortOrganizationDto mapToShortOrganizationDto(Organization organization);
}