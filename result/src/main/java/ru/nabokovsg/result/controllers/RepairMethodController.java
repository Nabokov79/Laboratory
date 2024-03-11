package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.result.dto.repairMethod.FullRepairMethodDto;
import ru.nabokovsg.result.dto.repairMethod.RepairMethodDto;
import ru.nabokovsg.result.services.RepairMethodService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/repair/method",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Способы ремонта",
        description="API для работы с данными способов ремонта оборудования")
public class RepairMethodController {

    private final RepairMethodService service;

    @Operation(summary = "Добавление способа ремонта")
    @PostMapping
    public ResponseEntity<FullRepairMethodDto> save(
                                  @RequestBody @Parameter(description = "Способ ремонта") RepairMethodDto methodDto) {
        return ResponseEntity.ok().body(service.save(methodDto));
    }

    @Operation(summary = "Изменение данных способа ремонта")
    @PatchMapping
    public ResponseEntity<FullRepairMethodDto> update(
                                  @RequestBody @Parameter(description = "Способ ремонта") RepairMethodDto methodDto) {
        return ResponseEntity.ok().body(service.update(methodDto));
    }

    @Operation(summary = "Получить способы ремонта по типу объекта")
    @GetMapping("/{id}")
    public ResponseEntity<List<FullRepairMethodDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить способ ремонта")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Способ ремонта оборудования успешно удален.");
    }
}