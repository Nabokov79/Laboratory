package ru.nabokovsg.company.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.building.BuildingDto;
import ru.nabokovsg.company.dto.building.FullBuildingDto;
import ru.nabokovsg.company.dto.building.ShortBuildingDto;
import ru.nabokovsg.company.exceptions.BadRequestException;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mappers.BuildingMapper;
import ru.nabokovsg.company.models.Building;
import ru.nabokovsg.company.models.QBuilding;
import ru.nabokovsg.company.models.enums.BuildingType;
import ru.nabokovsg.company.repository.BuildingRepository;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository repository;
    private final BuildingMapper mapper;
    private final ExploitationRegionService regionService;
    private final AddressService addressService;
    private final EntityManager entityManager;
    private final EmployeeService employeeService;

    @Override
    public ShortBuildingDto save(BuildingDto buildingDto) {
        return mapper.mapToShortBuildingDto(
                Objects.requireNonNullElseGet(getDuplicateByPredicate(buildingDto)
                        , () -> repository.save(
                                mapper.mapToBuilding(buildingDto
                                                   , employeeService.getDivisionContact(buildingDto.getEmployeeId())
                                                   , getBuildingType(buildingDto.getBuildingType())
                                                   , addressService.get(buildingDto.getAddressId())
                                                   , regionService.getById(buildingDto.getExploitationRegionId())))));
    }

    @Override
    public ShortBuildingDto update(BuildingDto buildingDto) {
        if (repository.existsById(buildingDto.getId())) {
            return mapper.mapToShortBuildingDto(
                    repository.save(mapper.mapToBuilding(buildingDto
                                                       , employeeService.getDivisionContact(buildingDto.getEmployeeId())
                                                       , getBuildingType(buildingDto.getBuildingType())
                                                       , addressService.get(buildingDto.getAddressId())
                                                       , regionService.getById(buildingDto.getExploitationRegionId())))
            );
        }
        throw new NotFoundException(String.format("Building with id=%s not found for update.", buildingDto.getId()));
    }

    @Override
    public FullBuildingDto get(Long id) {
        return mapper.mapToFullBuildingDto(getById(id));
    }

    @Override
    public Building getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Building with id=%s not found", id)));
    }

    @Override
    public List<ShortBuildingDto> getAll(Long id) {
        return repository.findAllByExploitationRegionId(id)
                         .stream()
                         .map(mapper::mapToShortBuildingDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Building with id=%s not found for delete.", id));
    }

    private Building getDuplicateByPredicate(BuildingDto buildingDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QBuilding.building.exploitationRegion.id.eq(buildingDto.getExploitationRegionId()));
        booleanBuilder.and(QBuilding.building.address.id.eq(buildingDto.getAddressId()));
        booleanBuilder.and(QBuilding.building.buildingType.eq( getBuildingType(buildingDto.getBuildingType())));
        if (buildingDto.getLogin() != null) {
            booleanBuilder.and(QBuilding.building.login.eq(buildingDto.getLogin()));
        }
        QBuilding building = QBuilding.building;
        return new JPAQueryFactory(entityManager).from(building)
                .select(building)
                .where(booleanBuilder)
                .fetchOne();
    }

    private BuildingType getBuildingType(String buildingType) {
        return BuildingType.from(buildingType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown type=%s", buildingType)));
    }
}