package ru.nabokovsg.lab_nk.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.lab_nk.dto.taskJournal.ResponseTaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TaskJournalDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TaskSearchParameters;
import ru.nabokovsg.lab_nk.services.TaskJournalService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/journal",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Жернал выполненных работ",
        description="API для работы с данными журнала выполненных работ")
public class TaskJournalController {

    private final TaskJournalService service;

    @Operation(summary = "Добавление данных новой задачи на выполнение работы")
    @PostMapping
    public ResponseEntity<ResponseTaskJournalDto> save(
            @RequestBody
            @Parameter(description = "Задача на выполнение работы") TaskJournalDto taskJournalDto) {
        return ResponseEntity.ok().body(service.save(taskJournalDto));
    }

    @Operation(summary = "Изменение данных задачи на выполнение работы")
    @PatchMapping
    public ResponseEntity<ResponseTaskJournalDto> update(
            @RequestBody
            @Parameter(description = "Задача на выполнение работы") TaskJournalDto taskJournalDto) {
        return ResponseEntity.ok().body(service.update(taskJournalDto));
    }

    @Operation(summary = "Получение задачи")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTaskJournalDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных задачи на выполнение работы")
    @GetMapping("/all")
    public ResponseEntity<List<ResponseTaskJournalDto>> getAll(
              @RequestParam(value = "employeeId", required = false)
              @Parameter(description = "Индентификатор сотрудника") Long employeeId
            , @RequestParam(value = "status", required = false)
              @Parameter(description = "Статус выполнения работы") String status
            , @RequestParam(value = "startPeriod", required = false)
              @Parameter(description = "Начало периода для выборки данных") LocalDate startPeriod
            , @RequestParam(value = "endPeriod", required = false)
              @Parameter(description = "Окончание периода для выборки данных") LocalDate endPeriod) {
        return ResponseEntity.ok().body(service.getAll(new TaskSearchParameters(employeeId, status,
                                                                                startPeriod, endPeriod)));
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Задача успешно удалена.");
    }
}