package ru.nabokovsg.company.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.placeWork.PlaceWorkDto;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mappers.PlaceWorkMapper;
import ru.nabokovsg.company.models.PlaceWork;
import ru.nabokovsg.company.repository.PlaceWorkRepository;

@Service
@RequiredArgsConstructor
public class PlaceWorkServiceImpl implements PlaceWorkService {

    private final PlaceWorkRepository repository;
    private final PlaceWorkMapper mapper;
    private final OrganizationService organizationService;
    private final BranchService branchService;
    private final DepartmentService departmentService;
    private final ExploitationRegionService exploitationRegionService;
    private final BuildingService buildingService;

    @Override
    public PlaceWork save(PlaceWorkDto placeWorkDto) {
        PlaceWork placeWork = mapper.mapToNewPlaceWork(organizationService.getById(placeWorkDto.getOrganizationId())
                                                     , branchService.getById(placeWorkDto.getBranchId()));
        add(placeWork, placeWorkDto);
        return repository.save(placeWork);
    }

    @Override
    public PlaceWork update(PlaceWorkDto placeWorkDto) {
        if (repository.existsById(placeWorkDto.getId())) {
            PlaceWork placeWork = mapper.mapToUpdatePlaceWork(placeWorkDto.getId()
                    , organizationService.getById(placeWorkDto.getOrganizationId())
                    , branchService.getById(placeWorkDto.getBranchId()));
            add(placeWork, placeWorkDto);
            return repository.save(placeWork);
        }
        throw new NotFoundException(String.format("PlaceWork with id=%s not found for update", placeWorkDto.getId()));
    }

    private void add(PlaceWork placeWork, PlaceWorkDto placeWorkDto) {
        if (placeWorkDto.getExploitationRegionId() != null && placeWorkDto.getExploitationRegionId() > 0) {
            mapper.mapFromDepartment(placeWork, departmentService.getById(placeWorkDto.getDepartmentId()));
        }
        if (placeWorkDto.getExploitationRegionId() != null && placeWorkDto.getExploitationRegionId() > 0) {
            mapper.mapFromExploitationRegion(placeWork
                    , exploitationRegionService.getById(placeWorkDto.getExploitationRegionId()));
        }
        if (placeWorkDto.getBuildingId() != null && placeWorkDto.getBuildingId() > 0) {
            mapper.mapFromBuilding(placeWork, buildingService.getById(placeWorkDto.getBuildingId()));
        }
    }
}