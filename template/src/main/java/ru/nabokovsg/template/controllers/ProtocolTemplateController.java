package ru.nabokovsg.template.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.template.dto.protocol.ResponseProtocolTemplateDto;
import ru.nabokovsg.template.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.template.dto.protocol.ShortResponseProtocolTemplateDto;
import ru.nabokovsg.template.services.ProtocolTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/protocol",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Шаблон протокола/заключения",
        description="API для работы с данными шаблона протокола/заключения")
public class ProtocolTemplateController {

    private final ProtocolTemplateService service;

    @Operation(summary = "Данные нового шаблона протокола/заключения")
    @PostMapping
    public ResponseEntity<ShortResponseProtocolTemplateDto> save(
            @RequestBody @Parameter(description = "Шаблон протокола/заключения") ProtocolTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.save(protocolDto));
    }

    @Operation(summary = "Данные для изменения информации в шаблоне протокола/заключения")
    @PatchMapping
    public ResponseEntity<ShortResponseProtocolTemplateDto> update(
            @RequestBody @Parameter(description = "Шаблон протокола/заключения") ProtocolTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.update(protocolDto));
    }

    @Operation(summary = "Получить данные шаблона протокола/заключения")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseProtocolTemplateDto> get(@PathVariable
                                                           @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить краткие данные шаблонов протоколов/заключений")
    @GetMapping("/all")
    public ResponseEntity<List<ShortResponseProtocolTemplateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
}