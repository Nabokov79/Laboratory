package ru.nabokovsg.lab_nk.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.lab_nk.dto.headerDocument.ResponseHeaderDocumentDto;
import ru.nabokovsg.lab_nk.dto.headerDocument.HeaderDocumentDto;
import ru.nabokovsg.lab_nk.services.HeaderDocumentService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/document/header",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Виды документов лаборатории НК",
        description="API для работы с данными видов документов лаборатории НК")
public class HeaderDocumentController {

    private final HeaderDocumentService service;

    @Operation(summary = "Добавление нового вида документа")
    @PostMapping
    public ResponseEntity<ResponseHeaderDocumentDto> save(
              @RequestBody @Parameter(description = "Вид документа лаборатории") HeaderDocumentDto headerDocumentDto) {
        return ResponseEntity.ok().body(service.save(headerDocumentDto));
    }

    @Operation(summary = "Изменение данных вида документа")
    @PatchMapping
    public ResponseEntity<ResponseHeaderDocumentDto> update(
              @RequestBody @Parameter(description = "Вид документа лаборатории") HeaderDocumentDto headerDocumentDto) {
        return ResponseEntity.ok().body(service.update(headerDocumentDto));
    }

    @Operation(summary = "Получение данных типов отчетных документов")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseHeaderDocumentDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных типов отчетных документов")
    @GetMapping
    public ResponseEntity<List<ResponseHeaderDocumentDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление вида отчетного документа")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable
                                         @Parameter(description = "Индентификатор документа") Long id) {
        service.delete(id);
        return ResponseEntity.ok("удалено");
    }
}