package ru.nabokovsg.lab_nk.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.client.LadNKClient;
import ru.nabokovsg.lab_nk.client.dto.*;
import ru.nabokovsg.lab_nk.dto.taskJournal.FullTaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TaskSearchParameters;
import ru.nabokovsg.lab_nk.exceptions.BadRequestException;
import ru.nabokovsg.lab_nk.exceptions.NotFoundException;
import ru.nabokovsg.lab_nk.mappers.TaskJournalMapper;
import ru.nabokovsg.lab_nk.models.QLaboratoryEmployee;
import ru.nabokovsg.lab_nk.models.TasksJournal;
import ru.nabokovsg.lab_nk.models.enums.Status;
import ru.nabokovsg.lab_nk.repository.TaskJournalRepository;
import ru.nabokovsg.lab_nk.models.QTasksJournal;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskJournalServiceImpl implements TaskJournalService {

    private final TaskJournalRepository repository;
    private final TaskJournalMapper mapper;
    private final LadNKClient client;
    private final EntityManager em;
    private final DocumentService documentService;

    @Override
    public FullTaskJournalDto save(TaskJournalDto taskJournalDto) {
        TasksJournal task = repository.findByDateAndEquipmentId(taskJournalDto.getDate(),
                taskJournalDto.getEquipmentId());
        if (task == null) {
                 task = repository.save(getTasksJournalData(taskJournalDto));
                 documentService.create(task);
            return mapper.mapToFullTasksJournalDto(task);
        }
        throw new BadRequestException(
                String.format("A record with the data: date=%s, building=%s, address=%s, equipment=%s exists"
                                , task.getDate(), task.getBuilding(), task.getAddress(), task.getEquipment())
        );
    }

    @Override
    public FullTaskJournalDto update(TaskJournalDto taskJournalDto) {
        documentService.validByTasksJournalId(taskJournalDto.getId());
        if (repository.existsById(taskJournalDto.getId())) {
            return mapper.mapToFullTasksJournalDto(repository.save(getTasksJournalData(taskJournalDto)));
        }
        throw new NotFoundException(
                String.format("TasksJournal with id=%s not found for update", taskJournalDto.getId())
        );
    }

    @Override
    public FullTaskJournalDto get(Long id) {
        return mapper.mapToFullTasksJournalDto(
               repository.findById(id)
                      .orElseThrow(() ->  new NotFoundException(String.format("TasksJournal with id=%s not found", id)))
        );
    }

    @Override
    public List<FullTaskJournalDto> getAll(TaskSearchParameters parameters) {
        QTasksJournal tasksJournal = QTasksJournal.tasksJournal;
        return new JPAQueryFactory(em).from(tasksJournal)
                                      .select(tasksJournal)
                                      .where(getPredicate(parameters))
                                      .fetch()
                                      .stream()
                                      .map(mapper::mapToFullTasksJournalDto)
                                      .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("TasksJournal with id=%s not found for delete", id));
    }

    private BooleanBuilder getPredicate(TaskSearchParameters parameters) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (parameters.getEmployeeId() != null && parameters.getEmployeeId() > 0) {
            booleanBuilder.and(QLaboratoryEmployee.laboratoryEmployee.id.eq(parameters.getEmployeeId()));
        }
        if (parameters.getStatus() != null) {
            Status status = Status.from(parameters.getStatus())
                    .orElseThrow(() -> new BadRequestException(
                            String.format("Unknown type=%s", parameters.getStatus())));
            booleanBuilder.and(QTasksJournal.tasksJournal.status.eq(status));
        }
        if (parameters.getStartPeriod() != null && parameters.getEndPeriod() != null) {
            booleanBuilder.and(QTasksJournal.tasksJournal.date.after(parameters.getStartPeriod()));
            booleanBuilder.and(QTasksJournal.tasksJournal.date.before(parameters.getEndPeriod()));
        } else {
            booleanBuilder.and(QTasksJournal.tasksJournal.date.eq(LocalDate.now()));
        }
        return booleanBuilder;
    }

    private TasksJournal getTasksJournalData(TaskJournalDto taskJournalDto) {
        FullBranchDto branchDto = client.getBranch(taskJournalDto.getBranchId());
        FullBuildingDto buildingDto = branchDto.getHeatSupplyAreas()
                                               .stream()
                                               .map(FullExploitationRegionDto::getBuildings)
                                               .flatMap(Collection::stream)
                                               .collect(Collectors.toMap(FullBuildingDto::getId, b -> b))
                                               .get(taskJournalDto.getBranchId());
        String branch = branchDto.getFullName();
        String building = String.join(" ", buildingDto.getBuildingType(), buildingDto.getLogin());
        String address = getStringAddress(buildingDto.getAddress());
        String equipment = getStringEquipment(client.getEquipment(taskJournalDto.getEquipmentId()));
        return mapper.mapToTaskJournal(taskJournalDto, branch, building, address, equipment);
    }

    private String getStringAddress(FullAddressDto address) {
        String string = String.join(", ", address.getCity()
                , String.join(" ", address.getStreet()
                        , "д.", String.valueOf(address.getHouseNumber())));
        if (address.getBuildingNumber() != null) {
            string = String.join(", ", string, String.join(""
                    , "корп.", String.valueOf(address.getBuildingNumber())));
        }
        if (address.getLetter() != null) {
            string = String.join(", ", string, String.join("", "лит.", address.getLetter()));
        }
        if (address.getIndex() != null) {
            return String.join(", ", String.valueOf(address.getIndex()), string);
        } else {
            return string;
        }
    }

    private String getStringEquipment(FullEquipmentDto equipment) {
        String name = equipment.getFullName();
        if (equipment.getModel() != null) {
            name = String.join(" ", name, equipment.getModel());
        }
        if (equipment.getStationaryNumber() != null) {
            name = String.join(" ", name, "cт. № ", String.valueOf(equipment.getStationaryNumber()));
        }
        if (equipment.getVolume() != null) {
            name = String.join(" ", name, "V = ", String.valueOf(equipment.getVolume()), "м3");
        }
        return name;
    }
}