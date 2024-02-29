package ru.nabokovsg.lab_nk.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.lab_nk.dto.employees.FullLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.dto.employees.ShortLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.services.LaboratoryEmployeeService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/employee",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сотрудники лаборатории НК",
        description="API для работы с сервисом работы с данными сотрудников лаборатории НК")
public class LaboratoryEmployeeController {

    private final LaboratoryEmployeeService service;

    @Operation(summary = "Добавление данных нового подр")
    @PostMapping("/{id}")
    public ResponseEntity<List<ShortLaboratoryEmployeeDto>> save(
              @PathVariable
              @Parameter(description = "Индентификатор структурного подразделения") Long id
            , @RequestParam(name = "divisionType")
              @Parameter(description = "Тип структурного подразделения") String divisionType) {
        return ResponseEntity.ok().body(service.save(id, divisionType));
    }

    @Operation(summary = "Получение данных сотрудника")
    @GetMapping("/{id}")
    public ResponseEntity<FullLaboratoryEmployeeDto> get(@PathVariable
                                                         @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных всех сотрудников")
    @GetMapping("/all")
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