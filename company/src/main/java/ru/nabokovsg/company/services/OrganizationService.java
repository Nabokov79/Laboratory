package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.organization.FullOrganizationDto;
import ru.nabokovsg.company.dto.organization.OrganizationDto;
import ru.nabokovsg.company.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.company.models.Licenses;
import ru.nabokovsg.company.models.Organization;
import java.util.List;

public interface OrganizationService {

    FullOrganizationDto save(OrganizationDto organizationDto);

    FullOrganizationDto update(OrganizationDto organizationDto);

    FullOrganizationDto get(Long id);

    Organization getById(Long id);

    List<ShortOrganizationDto> getAll();

    void addLicense(Long id, Licenses license);

    void delete(Long id);
}