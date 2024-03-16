package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.branch.BranchDto;
import ru.nabokovsg.company.dto.branch.ResponseBranchDto;
import ru.nabokovsg.company.dto.branch.ShortResponseBranchDto;
import ru.nabokovsg.company.models.Branch;
import ru.nabokovsg.company.models.Licenses;

import java.util.List;

public interface BranchService {

    ShortResponseBranchDto save(BranchDto branchDto);

    ShortResponseBranchDto update(BranchDto branchDto);

    ResponseBranchDto get(Long id);

    Branch getById(Long id);

    List<ShortResponseBranchDto> getAll(Long id);

    void addLicense(Long id, Licenses license);

    void  delete(Long id);
}