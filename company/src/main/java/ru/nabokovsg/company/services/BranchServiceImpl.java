package ru.nabokovsg.company.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.branch.BranchDto;
import ru.nabokovsg.company.dto.branch.FullBranchDto;
import ru.nabokovsg.company.dto.branch.ShortBranchDto;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mappers.BranchMapper;
import ru.nabokovsg.company.models.Branch;
import ru.nabokovsg.company.models.Licenses;
import ru.nabokovsg.company.repository.BranchRepository;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository repository;
    private final BranchMapper mapper;
    private final OrganizationService organizationService;
    private final AddressService addressService;

    @Override
    public ShortBranchDto save(BranchDto branchDto) {
        return mapper.mapToShortBranchDto(
                Objects.requireNonNullElseGet(repository.findByFullName(branchDto.getFullName()), () -> repository.save(
                        mapper.mapToNewBranch(branchDto
                                            , addressService.get(branchDto.getAddressId())
                                            , organizationService.getById(branchDto.getOrganizationId())))
                )
        );
    }

    @Override
    public ShortBranchDto update(BranchDto branchDto) {
        if (repository.existsById(branchDto.getId())) {
            return mapper.mapToShortBranchDto(
                    repository.save(mapper.mapToUpdateBranch(branchDto
                                                           , addressService.get(branchDto.getAddressId())
                                                           , organizationService.getById(branchDto.getOrganizationId()))
                    )
            );
        }
        throw new NotFoundException(
                               String.format("Branch wth name=%s not found for update", branchDto.getFullName()));
    }

    @Override
    public FullBranchDto get(Long id) {
        return mapper.mapToBranchDto(getById(id));
    }

    @Override
    public List<ShortBranchDto> getAll(Long id) {
        return repository.findAllByOrganization(id)
                         .stream()
                         .map(mapper::mapToShortBranchDto)
                         .toList();
    }

    @Override
    public void addLicense(Long id, Licenses license) {
        Branch branch = getById(id);
        branch.getLicenses().add(license);
        repository.save(branch);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Branch wth id=%s not found for delete", id));
    }

    @Override
    public Branch getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Branch wth id=%s not found", id)));

    }
}