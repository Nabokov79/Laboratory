package ru.nabokovsg.equipment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.equipment.dto.equipmentType.ResponseEquipmentTypeDto;
import ru.nabokovsg.equipment.service.EquipmentTypeService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/equipments/type",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Оборудование",
        description="API для работы с данными оборудования котельных, ЦТП")
public class EquipmentTypeController {

    private EquipmentTypeService service;


    @Operation(summary = "Получить все оборудование котельной, ЦТП")
    @GetMapping("/all")
    public ResponseEntity<List<ResponseEquipmentTypeDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
}