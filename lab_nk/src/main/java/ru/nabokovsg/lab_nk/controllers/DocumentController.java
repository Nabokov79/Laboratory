package ru.nabokovsg.lab_nk.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.lab_nk.dto.document.DocumentSearchParam;
import ru.nabokovsg.lab_nk.dto.document.FullDocumentDto;
import ru.nabokovsg.lab_nk.services.DocumentService;

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

    @Operation(summary = "Получение данных отчетного документа")
    @GetMapping("/{id}")
    public ResponseEntity<FullDocumentDto> get(
            @RequestParam
            @Parameter(description = "Индентификатор записи в журнале задач, замечания") Long id,
            @RequestParam(value = "type")
            @Parameter(description = "Тип данных для поиска по индентификатору") String type) {
        return ResponseEntity.ok().body(service.get(id, type));
    }
    @Operation(summary = "Получение данных отчетных документов")
    @GetMapping("/all")
    public ResponseEntity<List<FullDocumentDto>> getAll(
           @RequestParam(value = "equipmentId", required = false)
           @Parameter(description = "Оборудование") Long equipmentId,
           @RequestParam(value = "addressId", required = false)
           @Parameter(description = "Адрес") Long addressId,
           @RequestParam(value = "startDate", required = false)
           @Parameter(description = "Дата начала периода, за который требуется выдать документ") LocalDate startDate,
           @RequestParam(value = "endDate", required = false)
           @Parameter(description = "Дата окончания периода, за который требуется выдать документ") LocalDate endDate) {
        return ResponseEntity.ok().body(service.getAll(new DocumentSearchParam(equipmentId, addressId
                                                                             , startDate, endDate)));
    }
}