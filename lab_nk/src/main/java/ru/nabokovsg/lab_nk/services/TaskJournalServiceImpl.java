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
import ru.nabokovsg.lab_nk.models.LaboratoryEmployee;
import ru.nabokovsg.lab_nk.models.QLaboratoryEmployee;
import ru.nabokovsg.lab_nk.models.TasksJournal;
import ru.nabokovsg.lab_nk.models.enums.Status;
import ru.nabokovsg.lab_nk.repository.TaskJournalRepository;
import ru.nabokovsg.lab_nk.models.QTasksJournal;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskJournalServiceImpl implements TaskJournalService {

    private final TaskJournalRepository repository;
    private final TaskJournalMapper mapper;
    private final LadNKClient client;
    private final EntityManager em;
    private final HeaderDocumentService headerDocumentService;
    private final StringBuilderService builderService;
    private final LaboratoryEmployeeService employeeService;

    @Override
    public FullTaskJournalDto save(TaskJournalDto taskJournalDto) {
        TasksJournal task = repository.findByDateAndEquipmentId(taskJournalDto.getDate(),
                taskJournalDto.getEquipmentId());
        if (task == null) {
            task = repository.save(getTasksJournalData(taskJournalDto));
            if (task.getDate() != null) {
                client.createDocumentData(mapper.mapToFullTasksJournalDto(task));
            }
        }
        if (task.getDate() != null) {
            client.createDocumentData(mapper.mapToFullTasksJournalDto(task));
        }
        return mapper.mapToFullTasksJournalDto(task);
    }

    @Override
    public FullTaskJournalDto update(TaskJournalDto taskJournalDto) {
        TasksJournal taskJournal = getById(taskJournalDto.getId());
        if (taskJournal.getDocumentId() == null) {
            return mapper.mapToFullTasksJournalDto(repository.save(getTasksJournalData(taskJournalDto)));
        }
        throw new NotFoundException(
                String.format("The task cannot be updated, a document with an ID = %s has been created for this task", taskJournal.getDocumentId())
        );
    }

    @Override
    public FullTaskJournalDto get(Long id) {
        return mapper.mapToFullTasksJournalDto(getById(id));
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

    private TasksJournal getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->  new NotFoundException(String.format("TasksJournal with id=%s not found", id)));
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
        TasksJournal taskJournal = mapper.mapToTaskJournal(taskJournalDto);
        BranchDto branchDto = client.getBranch(taskJournal.getBranchId());
        EquipmentDto equipment = client.getEquipment(taskJournal.getEquipmentId());
        List<Long> ids = taskJournalDto.getEmployeesId();
        ids.add(taskJournalDto.getChiefId());
        Map<Long, LaboratoryEmployee> employees = employeeService.getByAllById(ids)
                                                          .stream()
                                                          .collect(Collectors.toMap(LaboratoryEmployee::getId, l -> l));
        taskJournal.setBranch(branchDto.getFullName());
        taskJournal.setBuilding(builderService.buildFromBuilding(branchDto.getHeatSupplyAreas()
                .stream()
                .map(ExploitationRegionDto::getBuildings)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(BuildingDto::getId, b -> b))
                .get(taskJournal.getBranchId())));
        taskJournal.setEquipment(builderService.getStringEquipment(equipment));
        taskJournal.setChief(employees.get(taskJournalDto.getChiefId()));
        taskJournal.setEmployees(employees.values()
                                          .stream()
                                          .filter( e -> taskJournalDto.getEmployeesId().contains(e.getId()))
                                          .collect(Collectors.toSet()));
        taskJournal.setHeaderDocument(headerDocumentService.getById(taskJournalDto.getHeaderDocumentId()));
        return taskJournal;
    }
}