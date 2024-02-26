package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.company.dto.branch.BranchDto;
import ru.nabokovsg.company.dto.branch.FullBranchDto;
import ru.nabokovsg.company.dto.branch.ShortBranchDto;
import ru.nabokovsg.company.models.Address;
import ru.nabokovsg.company.models.Branch;
import ru.nabokovsg.company.models.Organization;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(source = "branchDto.fullName", target = "fullName")
    @Mapping(source = "branchDto.shortName", target = "shortName")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "organization", target = "organization")
    @Mapping(target = "id", ignore = true)
    Branch mapToNewBranch(BranchDto branchDto, Address address, Organization organization);

    @Mapping(source = "branchDto.fullName", target = "fullName")
    @Mapping(source = "branchDto.shortName", target = "shortName")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "organization", target = "organization")
    @Mapping(source = "branchDto.id", target = "id")
    Branch mapToUpdateBranch(BranchDto branchDto, Address address, Organization organization);

    FullBranchDto mapToBranchDto(Branch branch);

    ShortBranchDto mapToShortBranchDto(Branch branch);
}