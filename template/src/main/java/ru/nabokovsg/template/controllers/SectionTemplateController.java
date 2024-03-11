package ru.nabokovsg.template.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.template.dto.section.SectionDataTemplateDto;
import ru.nabokovsg.template.dto.section.FullSectionTemplateDto;
import ru.nabokovsg.template.dto.section.SectionTemplateDto;
import ru.nabokovsg.template.dto.section.ShortSectionTemplateDto;
import ru.nabokovsg.template.services.SectionTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/section",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Шаблон раздела отчета",
        description="API для работы с данными шаблона раздела")
public class SectionTemplateController {

    private final SectionTemplateService service;

    @Operation(summary = "Добавить новые разделы")
    @PostMapping
    public ResponseEntity<List<ShortSectionTemplateDto>> save(
            @RequestBody @Parameter(description = "Данные шаблона разделов") SectionDataTemplateDto sectionsDto) {
        return ResponseEntity.ok().body(service.save(sectionsDto));
    }

    @Operation(summary = "Изменение данных разделов")
    @PatchMapping
    public ResponseEntity<List<ShortSectionTemplateDto>> update(
            @RequestBody @Parameter(description = "Данные шаблона разделов") List<SectionTemplateDto> sectionsDto) {
        return ResponseEntity.ok().body(service.update(sectionsDto));
    }

    @Operation(summary = "Получить раздел")
    @GetMapping("/{id}")
    public ResponseEntity<FullSectionTemplateDto> get(@PathVariable
                                                      @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить разделы отчета")
    @GetMapping("/all/report/{id}")
    public ResponseEntity<List<ShortSectionTemplateDto>> getAll(
                                             @PathVariable @Parameter(description = "Индентификатор отчета") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить раздел")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Шаблон раздела успешно удален.");
    }
}