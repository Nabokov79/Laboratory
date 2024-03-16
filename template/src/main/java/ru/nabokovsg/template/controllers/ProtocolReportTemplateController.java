package ru.nabokovsg.template.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.template.dto.protocolReport.ResponseProtocolReportTemplateDto;
import ru.nabokovsg.template.dto.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.template.dto.protocolReport.ShortResponseProtocolReportTemplateDto;
import ru.nabokovsg.template.services.ProtocolReportTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/report/protocol",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон протокола отчета",
        description="API для работы с данными шаблона протокола отчета")
public class ProtocolReportTemplateController {

    private final ProtocolReportTemplateService service;

    @Operation(summary = "Шаблон нового протокола")
    @PostMapping
    public ResponseEntity<ShortResponseProtocolReportTemplateDto> save(
            @RequestBody @Parameter(name = "Шаблон протокола в составе отчета") ProtocolReportTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.save(protocolDto));
    }

    @Operation(summary = "Изменение шаблона протокола")
    @PatchMapping
    public ResponseEntity<ShortResponseProtocolReportTemplateDto> update(
            @RequestBody @Parameter(name = "Шаблон протокола в составе отчета") ProtocolReportTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.update(protocolDto));
    }

    @Operation(summary = "Получить шаюлон протоколов")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseProtocolReportTemplateDto> get(
                                                    @PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить краткие данные протоколов")
    @GetMapping("/all/section/{id}")
    public ResponseEntity<List<ShortResponseProtocolReportTemplateDto>> getAll(
                                            @PathVariable @Parameter(description = "Индентификатор раздела") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить шаблон протокола")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Шаблон протокола успешно удален.");
    }
}