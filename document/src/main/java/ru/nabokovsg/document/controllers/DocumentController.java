package ru.nabokovsg.document.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.document.dto.document.TaskJournalDto;
import ru.nabokovsg.document.dto.document.DocumentSearchParam;
import ru.nabokovsg.document.dto.document.ResponseDocumentDto;
import ru.nabokovsg.document.service.DocumentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/document",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Журнал выполненных задач",
        description="API для работы с данными журнала выполненных работ")
public class DocumentController {

    private final DocumentService service;

    @Operation(summary = "Добавление данных отчетного документа")
    @PostMapping
    public ResponseEntity<Long> save(TaskJournalDto taskJournalDto) {
        return ResponseEntity.ok().body(service.save(taskJournalDto));
    }

    @Operation(summary = "Получение данных отчетного документа")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDocumentDto> get(
            @PathVariable @Parameter(description = "Индентификатор записи в журнале задач") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }
    @Operation(summary = "Получение данных отчетных документов")
    @GetMapping("/all")
    public ResponseEntity<List<ResponseDocumentDto>> getAll(
           @RequestParam(value = "equipmentId", required = false)
           @Parameter(description = "Индентификатор записи в журнале") Long taskJournalId,
           @RequestParam(value = "chiefId", required = false)
           @Parameter(description = "Индентификатор руководителя работ") Long chiefId,
           @RequestParam(value = "inspectorId", required = false)
           @Parameter(description = "Индентификатор сотрудника") Long inspectorId,
           @RequestParam(value = "startDate", required = false)
           @Parameter(description = "Дата начала периода, за который требуется выдать документ") LocalDate startDate,
           @RequestParam(value = "endDate", required = false)
           @Parameter(description = "Дата окончания периода, за который требуется выдать документ") LocalDate endDate) {
        return ResponseEntity.ok().body(service.getAll(new DocumentSearchParam(taskJournalId, chiefId, inspectorId
                                                                             , startDate, endDate)));
    }
}