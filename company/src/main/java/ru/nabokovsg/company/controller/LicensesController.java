package ru.nabokovsg.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.company.dto.licenses.ResponseLicenseDto;
import ru.nabokovsg.company.dto.licenses.LicenseDto;
import ru.nabokovsg.company.services.LicenseService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/licenses",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Лицензия/Свидетельство",
        description="API для работы с данными лицензии/свидетельства")
public class LicensesController {

    private final LicenseService service;

    @Operation(summary = "Добавление данных лицензии/свидетельства")
    @PostMapping
    public ResponseEntity<ResponseLicenseDto> save(@RequestBody
                                           @Parameter(description = "Лицензия/Свидетельство") LicenseDto licenseDto) {
        return ResponseEntity.ok().body(service.save(licenseDto));
    }

    @Operation(summary = "Изменение данных лицензии/свидетельства")
    @PatchMapping
    public ResponseEntity<ResponseLicenseDto> update(@RequestBody
                                             @Parameter(description = "Лицензия/Свидетельство") LicenseDto licenseDto) {
        return ResponseEntity.ok().body(service.update(licenseDto));
    }

    @Operation(summary = "Получение данных всех лицензий/свидетельств")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ResponseLicenseDto>> getAll(
                                       @PathVariable
                                       @Parameter(description = "Индентификатор структурного подразделения") Long id
                                     , @RequestParam(name = "divisionType")
                                       @Parameter(description = "Тип структурного подразделения") String divisionType) {
        return ResponseEntity.ok().body(service.getAll(id, divisionType));
    }

    @Operation(summary = "Удаление данных эксплуатационного участка")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Лицензия/Свидетельство успешно удалены.");
    }
}