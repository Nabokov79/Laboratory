package ru.nabokovsg.template.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.template.dto.table.FullTableTemplateDto;
import ru.nabokovsg.template.dto.table.TableTemplateDto;
import ru.nabokovsg.template.services.TableTemplateService;

@RestController
@RequestMapping(
        value = "/table",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Шаблон таблицы",
        description="API для работы с данными шаблона таблицы")
public class TableTemplateController {

    private final TableTemplateService service;

    @Operation(summary = "Добавление нового шаблона таблицы")
    @PostMapping
    public ResponseEntity<FullTableTemplateDto> save(@RequestBody
                                         @Parameter(description = "Данные шаблона таблицы") TableTemplateDto tableDto) {
        return ResponseEntity.ok().body(service.save(tableDto));
    }

    @Operation(summary = "Изменение информации в шаблоне таблицы")
    @PatchMapping
    public ResponseEntity<FullTableTemplateDto> update(@RequestBody
                                   @Parameter(description = "Данные шаблона таблицы") TableTemplateDto tableDto) {
        return ResponseEntity.ok().body(service.update(tableDto));
    }

    @Operation(summary = "Получить таблицу")
    @GetMapping("/{id}")
    public ResponseEntity<FullTableTemplateDto> get(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Удалить таблицу")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Таблица успешно удалена.");
    }
}