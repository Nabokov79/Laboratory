package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.result.dto.geodesy.FullPermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.dto.geodesy.PermissibleDeviationsGeodesyDto;
import ru.nabokovsg.result.services.PermissibleDeviationsGeodesyService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/geodesy",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные допустимых значений при проведении геодезии(нивелирования)",
        description="API для работы с данными норм при проведении геодезии(нивелирования)")
public class PermissibleDeviationsGeodesyController {

    private final PermissibleDeviationsGeodesyService service;

    @Operation(summary = "Новая рекомендация для раздела отчета")
    @PostMapping
    public ResponseEntity<FullPermissibleDeviationsGeodesyDto> save(
            @RequestBody
            @Parameter(name = "Нормы по геодезии(нивелированию)") PermissibleDeviationsGeodesyDto geodesyDto) {
        return ResponseEntity.ok().body(service.save(geodesyDto));
    }

    @Operation(summary = "Изменение рекомендации")
    @PatchMapping
    public ResponseEntity<FullPermissibleDeviationsGeodesyDto> update(
            @RequestBody
            @Parameter(name = "Нормы по геодезии(нивелированию)") PermissibleDeviationsGeodesyDto geodesyDto) {
        return ResponseEntity.ok().body(service.update(geodesyDto));
    }

    @Operation(summary = "Получить рекомендации по типу объекта")
    @GetMapping("/{id}")
    public ResponseEntity<List<FullPermissibleDeviationsGeodesyDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить рекомендацию")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Нормы по геодезии(нивелированию) успешно удалены.");
    }
}