package ru.nabokovsg.company.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.organization.FullOrganizationDto;
import ru.nabokovsg.company.dto.organization.OrganizationDto;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mappers.OrganizationMapper;
import ru.nabokovsg.company.models.Licenses;
import ru.nabokovsg.company.models.Organization;
import ru.nabokovsg.company.repository.OrganizationRepository;
import ru.nabokovsg.company.dto.organization.ShortOrganizationDto;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper mapper;
    private final AddressService addressService;

    @Override
    public FullOrganizationDto save(OrganizationDto organizationDto) {
        return mapper.mapToOrganizationDto(
                Objects.requireNonNullElseGet(repository.findByFullName(organizationDto.getFullName())
                        , () -> repository.save(mapper.mapToNewOrganization(organizationDto
                                                                 , addressService.get(organizationDto.getAddressId()))))
        );
    }

    @Override
    public FullOrganizationDto update(OrganizationDto organizationDto) {
        if (repository.existsById(organizationDto.getId())) {
            return mapper.mapToOrganizationDto(
                    repository.save(
                            mapper.mapToUpdateOrganization(organizationDto
                                                         , addressService.get(organizationDto.getAddressId())))
            );
        }
        throw new NotFoundException(
                String.format("Organization with name=%s not found for update.", organizationDto.getFullName()));
    }

    @Override
    public FullOrganizationDto get(Long id) {
        return mapper.mapToOrganizationDto(getById(id));
    }

    @Override
    public List<ShortOrganizationDto> getAll() {
        return repository.findAllOrganization().stream()
                                               .map(mapper::mapToShortOrganizationDto)
                                               .toList();
    }

    @Override
    public void addLicense(Long id, Licenses license) {
        Organization organization = getById(id);
        organization.getLicenses().add(license);
        repository.save(organization);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
       throw new NotFoundException(String.format("Organization with id=%s not found for delete.", id));
    }

    @Override
    public Organization getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(
                                                    String.format("Organization with id=%s not found for license",id)));
    }
}