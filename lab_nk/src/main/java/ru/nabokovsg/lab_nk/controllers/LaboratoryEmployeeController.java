package ru.nabokovsg.lab_nk.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.lab_nk.dto.employees.DivisionDataDto;
import ru.nabokovsg.lab_nk.dto.employees.FullLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.dto.employees.ShortLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.services.LaboratoryEmployeeService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/lab-nk/employee",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сотрудники лаборатории НК",
        description="API для работы с сервисом работы с данными сотрудников лаборатории НК")
public class LaboratoryEmployeeController {

    private final LaboratoryEmployeeService service;

    @Operation(summary = "Добавление данных нового подр")
    @PostMapping
    public ResponseEntity<List<ShortLaboratoryEmployeeDto>> save(@RequestBody
                                                 @Parameter(description = "Данные для добавления сотрудников") DivisionDataDto divisionDataDto) {
        return ResponseEntity.ok().body(service.save(divisionDataDto));
    }

    @Operation(summary = "Получение данных сотрудника")
    @GetMapping("/{id}")
    public ResponseEntity<FullLaboratoryEmployeeDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных всех сотрудников")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ShortLaboratoryEmployeeDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление данных сотрудника")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные сотрудника удалены.");
    }
}