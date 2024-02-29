package ru.nabokovsg.lab_nk.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.lab_nk.dto.certificate.CertificateDto;
import ru.nabokovsg.lab_nk.dto.certificate.FullCertificateDto;
import ru.nabokovsg.lab_nk.services.CertificateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/certificates",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Сертификаты сотрудников",
     description="API для работы с данными сертификатов сотруднаков")
public class CertificateController {

    private final CertificateService service;

    @Operation(summary = "Добавление данных сертификатов сотрудника")
    @PostMapping
    public ResponseEntity<FullCertificateDto> save(
               @RequestBody @Parameter(description = "Список сертификатов сотрудника") CertificateDto certificateDto) {
        return ResponseEntity.ok().body(service.save(certificateDto));
    }

    @Operation(summary = "Изменение данных аттестации сотрудника")
    @PatchMapping
    public ResponseEntity<FullCertificateDto> update(
                @RequestBody @Parameter(description = "Список сертификатов сотрудника") CertificateDto certificateDto) {
        return ResponseEntity.ok().body(service.update(certificateDto));
    }

    @Operation(summary = "Получение данных сертификатов сотрудников")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<FullCertificateDto>> getAll(
                        @PathVariable @Parameter(description = "Индентификатор сотрудника") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удаление данных сертификата сотрудника")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Аттестация сотрудника удалена.");
    }
}