package ru.nabokovsg.template.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.template.dto.header.FullHeaderTemplateDto;
import ru.nabokovsg.template.dto.header.HeaderTemplateDto;
import ru.nabokovsg.template.services.HeaderTemplateService;

@RestController
@RequestMapping(
        value = "/header",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон заголовка титульного листа, протокола",
        description="API для работы с данными шаблона заголовка")
public class HeaderTemplateController {

    private final HeaderTemplateService service;

    @Operation(summary = "Добавление нового шаблона заголовка")
    @PostMapping
    public ResponseEntity<FullHeaderTemplateDto> save(
                        @RequestBody @Parameter(description = "Данные шаблона заголовка") HeaderTemplateDto headerDto) {
        return ResponseEntity.ok().body(service.save(headerDto));
    }

    @Operation(summary = "Изменение информации в шаблоне заголовка")
    @PatchMapping
    public ResponseEntity<FullHeaderTemplateDto> update(
                        @RequestBody @Parameter(description = "Данные шаблона заголовка") HeaderTemplateDto headerDto) {
        return ResponseEntity.ok().body(service.update(headerDto));
    }

    @Operation(summary = "Получить шаблон заголовка")
    @GetMapping("/{id}")
    public ResponseEntity<FullHeaderTemplateDto> get(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Удалить шаблон заголовка")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Таблица успешно удалена.");
    }
}