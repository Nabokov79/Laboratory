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
import ru.nabokovsg.result.dto.defects.FullDefectDto;
import ru.nabokovsg.result.services.DefectsService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/defects",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Дефекты элемента объекта",
        description="API для работы с дефектами элементов объекта")
public class DefectsController {

    private final DefectsService service;

    @Operation(summary = "Добавление новых дефектов объекта")
    @PostMapping
    public ResponseEntity<FullDefectDto> save(@RequestBody @Parameter(description = "Дефекты") DefectDto defectDto) {
        return ResponseEntity.ok().body(service.save(defectDto));
    }

    @Operation(summary = "Изменение данных дефектов объекта")
    @PatchMapping
    public ResponseEntity<FullDefectDto> update(@RequestBody @Parameter(description = "Дефект") DefectDto defectDto) {
        return ResponseEntity.ok().body(service.update(defectDto));
    }

    @Operation(summary = "Получить рекомендации по типу объекта")
    @GetMapping("/{id}")
    public ResponseEntity<List<FullDefectDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить рекомендацию")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные дефекта успешно удалены.");
    }
}