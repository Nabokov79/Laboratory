package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.result.dto.equipmentDiagnosed.EquipmentDiagnosedDto;
import ru.nabokovsg.result.services.EquipmentDiagnosedService;

@RestController
@RequestMapping(
        value = "/equipment/diagnosed",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные оборудования подлежащего диагностики",
        description="API для работы с данными оборудования подлежащего диагостики(обследованию)")
public class EquipmentDiagnosedController {

    private final EquipmentDiagnosedService service;

    @Operation(summary = "Добавить оборудование подлежащее диагостики(обследованию)")
    @PostMapping
    public ResponseEntity<HttpStatus> save(
            @RequestBody @Parameter(name = "Рекомендация") EquipmentDiagnosedDto equipmentDto) {
        service.save(equipmentDto);
        return ResponseEntity.ok().build();
    }
}