package ru.nabokovsg.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.company.dto.branch.BranchDto;
import ru.nabokovsg.company.dto.branch.FullBranchDto;
import ru.nabokovsg.company.dto.branch.ShortBranchDto;
import ru.nabokovsg.company.services.BranchService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/branch",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Филиал организации",
        description="API для работы с данными филиала")
public class BranchController {

    private final BranchService service;

    @Operation(summary = "Добавление данных филиала")
    @PostMapping
    public ResponseEntity<ShortBranchDto> save(@RequestBody @Parameter(description = "Филиал") BranchDto branchDto) {
        return ResponseEntity.ok().body(service.save(branchDto));
    }

    @Operation(summary = "Изменение данных филиала")
    @PatchMapping
    public ResponseEntity<ShortBranchDto> update(@RequestBody @Parameter(description = "Филиал") BranchDto branchDto) {
        return ResponseEntity.ok().body(service.update(branchDto));
    }

    @Operation(summary = "Получение данных филиала")
    @GetMapping("/{id}")
    public ResponseEntity<FullBranchDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение сведений о филиалах")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ShortBranchDto>> getAll(@PathVariable
                                                       @Parameter(description = "Индентификатор организации") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удаление данных филиала")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Филиал успешно удален.");
    }
}