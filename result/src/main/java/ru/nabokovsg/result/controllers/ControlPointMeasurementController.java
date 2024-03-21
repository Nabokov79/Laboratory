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
import ru.nabokovsg.result.dto.сontrolPoint.ControlPointMeasurementDto;
import ru.nabokovsg.result.services.ControlPointMeasurementService;


@RestController
@RequestMapping(
        value = "/measurement/geodesic/control-point",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Рекомендация по устранению дефектов, замечаний",
        description="API для работы с данными рекомендациями по устранению дефектов, замечаний")
public class ControlPointMeasurementController {

    private final ControlPointMeasurementService service;

    @Operation(summary = "Получить рассчитанные данные геодезических измерений реперов оборудования")
    @GetMapping("/{id}")
    public ResponseEntity<ControlPointMeasurementDto> get(
            @PathVariable @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }
}