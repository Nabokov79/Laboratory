package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.result.dto.acceptable.AcceptableThicknessDto;
import ru.nabokovsg.result.dto.acceptable.FullAcceptableThicknessDto;
import ru.nabokovsg.result.services.AcceptableThicknessService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/acceptable",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные допустимых толщин элементов оборудования",
        description="API для работы с данными норм для толщин элементов оборудования")
public class AcceptableThicknessController {

    private final AcceptableThicknessService service;

    @Operation(summary = "Новая рекомендация для раздела отчета")
    @PostMapping
    public ResponseEntity<FullAcceptableThicknessDto> save(
            @RequestBody @Parameter(name = "Рекомендация") AcceptableThicknessDto thicknessDto) {
        return ResponseEntity.ok().body(service.save(thicknessDto));
    }

    @Operation(summary = "Изменение рекомендации")
    @PatchMapping
    public ResponseEntity<FullAcceptableThicknessDto> update(
            @RequestBody @Parameter(name = "Рекомендация") AcceptableThicknessDto thicknessDto) {
        return ResponseEntity.ok().body(service.update(thicknessDto));
    }

    @Operation(summary = "Получить рекомендации по типу объекта")
    @GetMapping("/{id}")
    public ResponseEntity<List<FullAcceptableThicknessDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить рекомендацию")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Рекомендация успешно удалена.");
    }
}