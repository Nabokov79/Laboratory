package ru.nabokovsg.template.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.template.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.template.dto.conclusion.FullConclusionTemplateDto;
import ru.nabokovsg.template.services.ConclusionTemplateService;

@RestController
@RequestMapping(
        value = "/conclusion",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Шаблон заключений",
        description="API для работы с данными шаблона заключений к протоколам")
public class ConclusionTemplateController {

    private final ConclusionTemplateService service;

    @Operation(summary = "Данные шаблона новых заключений протокола отчета")
    @PostMapping
    public ResponseEntity<FullConclusionTemplateDto> save(
            @RequestBody @Parameter(name = "Шаблон заключений") ConclusionTemplateDto conclusionDto) {
        return ResponseEntity.ok().body(service.save(conclusionDto));
    }

    @Operation(summary = "Изменение данных шаблона заключений")
    @PatchMapping
    public ResponseEntity<FullConclusionTemplateDto> update(
            @RequestBody @Parameter(name = "Шаблон заключений") ConclusionTemplateDto conclusionDto) {
        return ResponseEntity.ok().body(service.update(conclusionDto));
    }

    @Operation(summary = "Удалить шаблон заключений")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Шаблоны заключений успешно удалены.");
    }
}