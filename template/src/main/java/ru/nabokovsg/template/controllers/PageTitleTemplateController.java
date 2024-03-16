package ru.nabokovsg.template.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.template.dto.pageTitle.ResponsePageTitleTemplateDto;
import ru.nabokovsg.template.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.template.services.PageTitleTemplateService;

@RestController
@RequestMapping(
        value = "/title-page",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Шаблон титульного листа",
        description="API для работы с данными шаблона титульного листа")
public class PageTitleTemplateController {

    private final PageTitleTemplateService service;

    @Operation(summary = "Данные титульного листа")
    @PostMapping
    public ResponseEntity<ResponsePageTitleTemplateDto> save(
            @RequestBody @Parameter(description = "Данные Титульного листа") PageTitleTemplateDto pageTitleDto) {
        return ResponseEntity.ok().body(service.save(pageTitleDto));
    }

    @Operation(summary = "Изменение данных титульного листа")
    @PatchMapping
    public ResponseEntity<ResponsePageTitleTemplateDto> update(
            @RequestBody @Parameter(description = "Данные Титульного листа") PageTitleTemplateDto pageTitleDto) {
        return ResponseEntity.ok().body(service.update(pageTitleDto));
    }
}