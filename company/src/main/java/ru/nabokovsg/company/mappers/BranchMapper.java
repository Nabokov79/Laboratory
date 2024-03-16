package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.company.dto.branch.BranchDto;
import ru.nabokovsg.company.dto.branch.ResponseBranchDto;
import ru.nabokovsg.company.dto.branch.ShortResponseBranchDto;
import ru.nabokovsg.company.models.Address;
import ru.nabokovsg.company.models.Branch;
import ru.nabokovsg.company.models.DivisionContact;
import ru.nabokovsg.company.models.Organization;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(source = "branchDto.fullName", target = "fullName")
    @Mapping(source = "branchDto.shortName", target = "shortName")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "divisionContact", target = "contact")
    @Mapping(source = "organization", target = "organization")
    @Mapping(source = "branchDto.id", target = "id")
    Branch mapToBranch(BranchDto branchDto, DivisionContact divisionContact, Address address, Organization organization);

    ResponseBranchDto mapToBranchDto(Branch branch);

    ShortResponseBranchDto mapToShortBranchDto(Branch branch);
}