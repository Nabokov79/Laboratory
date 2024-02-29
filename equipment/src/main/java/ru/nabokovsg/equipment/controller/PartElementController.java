package ru.nabokovsg.equipment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.equipment.dto.partElement.FullPartElementDto;
import ru.nabokovsg.equipment.dto.partElement.PartElementDto;
import ru.nabokovsg.equipment.dto.partElement.ShortPartElementDto;
import ru.nabokovsg.equipment.service.PartElementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/parts",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Подэлемент",
        description="API для работы с данными подэлементов элементов")
public class PartElementController {

    private final PartElementService service;

    @Operation(summary = "Добавление нового подэлемента")
    @PostMapping
    public ResponseEntity<FullPartElementDto> save(
            @RequestBody @Parameter(description = "Подэлемент") PartElementDto partElementDto) {
        return ResponseEntity.ok().body(service.save(partElementDto));
    }

    @Operation(summary = "Изменение данных подэлемента")
    @PatchMapping
    public ResponseEntity<FullPartElementDto> update(
            @RequestBody @Parameter(description = "Подэлемент") PartElementDto partElementDto) {
        return ResponseEntity.ok().body(service.update(partElementDto));
    }

    @Operation(summary = "Получить подэлемент")
    @GetMapping("/{id}")
    public ResponseEntity<FullPartElementDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все подэлементы элементы")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ShortPartElementDto>> getAll(
            @PathVariable @Parameter(description = "Индентификатор элемента") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удаление подэлемента")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Подэлемент успешно удален.");
    }
}