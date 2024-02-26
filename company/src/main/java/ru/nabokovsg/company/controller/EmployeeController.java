package ru.nabokovsg.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.company.dto.employee.EmployeeDto;
import ru.nabokovsg.company.dto.employee.FullEmployeeDto;
import ru.nabokovsg.company.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.company.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/employee",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Сотрудники",
     description="API для работы с данными сотрудников")
public class EmployeeController {

    private final EmployeeService service;

    @Operation(summary = "Добавление данных нового сотрудника")
    @PostMapping
    public ResponseEntity<ShortEmployeeDto> save(@RequestBody
                                                 @Parameter(description = "Сотрудник") EmployeeDto employeeDto) {
        return ResponseEntity.ok().body(service.save(employeeDto));
    }

    @Operation(summary = "Изменение данных сотрудника")
    @PatchMapping
    public ResponseEntity<ShortEmployeeDto> update(@RequestBody
                                                  @Parameter(description = "Сотрудник") EmployeeDto employeeDto) {
        return ResponseEntity.ok().body(service.update(employeeDto));
    }

    @Operation(summary = "Получение данных сотрудника")
    @GetMapping("/{id}")
    public ResponseEntity<FullEmployeeDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных всех сотрудников")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ShortEmployeeDto>> getAll(
                                       @PathVariable
                                       @Parameter(description = "Индентификатор структурного подразделения") Long id,
                                       @RequestParam(name = "divisionType")
                                       @Parameter(description = "Тип структурного подразделения") String divisionType) {
        return ResponseEntity.ok().body(service.getAll(id, divisionType));
    }

    @Operation(summary = "Удаление данных сотрудника")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные сотрудника удалены.");
    }
}