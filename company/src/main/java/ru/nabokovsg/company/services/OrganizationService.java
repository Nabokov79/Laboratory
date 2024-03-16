package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.organization.ResponseOrganizationDto;
import ru.nabokovsg.company.dto.organization.OrganizationDto;
import ru.nabokovsg.company.dto.organization.ShortResponseOrganizationDto;
import ru.nabokovsg.company.models.Licenses;
import ru.nabokovsg.company.models.Organization;
import java.util.List;

public interface OrganizationService {

    ShortResponseOrganizationDto save(OrganizationDto organizationDto);

    ShortResponseOrganizationDto update(OrganizationDto organizationDto);

    ResponseOrganizationDto get(Long id);

    Organization getById(Long id);

    List<ShortResponseOrganizationDto> getAll();

    void addLicense(Long id, Licenses license);

    void delete(Long id);
}