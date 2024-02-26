package ru.nabokovsg.equipment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.equipment.dto.element.ElementDto;
import ru.nabokovsg.equipment.dto.element.FullElementDto;
import ru.nabokovsg.equipment.dto.element.ShortElementDto;
import ru.nabokovsg.equipment.service.ElementService;


import java.util.List;

@RestController
@RequestMapping(
        value = "/elements",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Элемент",
        description="API для работы с данными элементов оборудования")
public class ElementController {

    private final ElementService service;

    @Operation(summary = "Добавление нового элемента")
    @PostMapping
    public ResponseEntity<ShortElementDto> save(
                                            @RequestBody @Parameter(description = "Элемент") ElementDto elementDto) {
        return ResponseEntity.ok().body(service.save(elementDto));
    }

    @Operation(summary = "Изменение данных элемента")
    @PatchMapping
    public ResponseEntity<ShortElementDto> update(
                                              @RequestBody @Parameter(description = "Элемент") ElementDto elementDto) {
        return ResponseEntity.ok().body(service.update(elementDto));
    }

    @Operation(summary = "Получить элемент")
    @GetMapping("/{id}")
    public ResponseEntity<FullElementDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все элементы оборудования")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ShortElementDto>> getAll(
                                @PathVariable @Parameter(description = "Индентификатор оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удаление элемента")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Элемент успешно удален.");
    }
}
