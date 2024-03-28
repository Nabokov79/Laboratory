package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.result.dto.defects.DefectDto;
import ru.nabokovsg.result.dto.defects.ResponseDefectDto;
import ru.nabokovsg.result.services.DefectsService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/defects",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Дефекты элемента оборудования",
        description="API для работы с дефектами элементов оборудования")
public class DefectsController {

    private final DefectsService service;

    @Operation(summary = "Добавление новых дефектов оборудования")
    @PostMapping
    public ResponseEntity<ResponseDefectDto> save(@RequestBody @Parameter(description = "Дефекты") DefectDto defectDto) {
        return ResponseEntity.ok().body(service.save(defectDto));
    }

    @Operation(summary = "Изменение данных дефектов оборудования")
    @PatchMapping
    public ResponseEntity<ResponseDefectDto> update(@RequestBody @Parameter(description = "Дефект") DefectDto defectDto) {
        return ResponseEntity.ok().body(service.update(defectDto));
    }

    @Operation(summary = "Получить дефекты по типу оборудования")
    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseDefectDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить дефект")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные дефекта успешно удалены.");
    }
}