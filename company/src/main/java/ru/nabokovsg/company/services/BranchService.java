package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.branch.BranchDto;
import ru.nabokovsg.company.dto.branch.FullBranchDto;
import ru.nabokovsg.company.dto.branch.ShortBranchDto;
import ru.nabokovsg.company.models.Branch;
import ru.nabokovsg.company.models.Licenses;

import java.util.List;

public interface BranchService {

    ShortBranchDto save(BranchDto branchDto);

    ShortBranchDto update(BranchDto branchDto);

    FullBranchDto get(Long id);

    Branch getById(Long id);

    List<ShortBranchDto> getAll(Long id);

    void addLicense(Long id, Licenses license);

    void  delete(Long id);
}