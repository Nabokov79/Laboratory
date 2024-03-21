package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.result.dto.referencePoint.ReferencePointDto;
import ru.nabokovsg.result.services.ReferencePointMeasurementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/measurement/geodesic/reference-point",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Рассчитанные значения реперов оборудования",
        description="API для работы с рассчитанных значений реперов оборудования")
public class ReferencePointMeasurementController {

    private final ReferencePointMeasurementService service;

    @Operation(summary = "Получить рассчитанные данные геодезических измерений реперов оборудования")
    @GetMapping("/{id}")
    public ResponseEntity<List<ReferencePointDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }
}