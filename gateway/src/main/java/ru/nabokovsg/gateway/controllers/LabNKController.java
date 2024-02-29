package ru.nabokovsg.gateway.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.LabNKClient;
import ru.nabokovsg.gateway.dto.labNk_service.NewCertificateDto;
import ru.nabokovsg.gateway.dto.labNk_service.UpdateCertificateDto;
import ru.nabokovsg.gateway.dto.labNk_service.documentation.NewDocumentationDto;
import ru.nabokovsg.gateway.dto.labNk_service.documentation.UpdateDocumentationDto;
import ru.nabokovsg.gateway.dto.labNk_service.headerDocument.NewHeaderDocumentDto;
import ru.nabokovsg.gateway.dto.labNk_service.headerDocument.UpdateHeaderDocumentDto;

import java.time.LocalDate;

@RestController
@RequestMapping(
        value = "/laboratory/lab-nk",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сервис данных структурного подразделения организации",
        description="API для работы с сервисом данными лаборатории неразрушающего контроля")
public class LabNKController {

    private final LabNKClient client;

    @Operation(summary = "Добавление данных сертификатов сотрудника")
    @PostMapping("/certificates")
    public Mono<Object> saveCertificate(
            @RequestBody @Valid
            @Parameter(description = "Список сертификатов сотрудника") NewCertificateDto certificateDto) {
        return client.saveCertificate(certificateDto);
    }

    @Operation(summary = "Изменение данных аттестации сотрудника")
    @PatchMapping("/certificates")
    public Mono<Object> updateCertificate(
            @RequestBody @Valid
            @Parameter(description = "Список сертификатов сотрудника") UpdateCertificateDto certificateDto) {
        return client.updateCertificate(certificateDto);
    }

    @Operation(summary = "Получение данных сертификатов сотрудников")
    @GetMapping("/certificates/employee/{id}")
    public Flux<Object> getAllCertificate(@PathVariable @NotNull @Positive
                                          @Parameter(description = "Индентификатор сотрудника") Long id) {
        return client.getAllCertificate(id);
    }

    @Operation(summary = "Удаление данных сертификата сотрудника")
    @DeleteMapping("/certificates/{id}")
    public Mono<String> deleteCertificate(@PathVariable @NotNull @Positive
                                          @Parameter(description = "Индентификатор") Long id) {
        return client.deleteCertificate(id);
    }

    @Operation(summary = "Добавление данных документа")
    @PostMapping("/documentation")
    public Mono<Object> saveDocumentation(@RequestBody @Valid
                                          @Parameter(description = "Документ") NewDocumentationDto documentationDto) {
        return client.saveDocumentation(documentationDto);
    }

    @Operation(summary = "Изменение данных документа")
    @PatchMapping("/documentation")
    public Mono<Object> updateDocumentation(@RequestBody @Valid
                                         @Parameter(description = "Документ") UpdateDocumentationDto documentationDto) {
        return client.updateDocumentation(documentationDto);
    }

    @Operation(summary = "Получение данных документов")
    @GetMapping("/documentation")
    public Flux<Object> getAll(@RequestParam(name = "number", required = false)
                               @Parameter(description = "Номер документа") String number,
                               @RequestParam(name = "title", required = false)
                               @Parameter(description = "Название документа") String title) {
        return client.getAllDocumentation(number, title);
    }

    @Operation(summary = "Удаление данных документа")
    @DeleteMapping("/documentation/{id}")
    public Mono<String> deleteDocumentation(@PathVariable @NotNull @Positive
                                            @Parameter(description = "Индентификатор") Long id) {
        return client.deleteDocumentation(id);
    }

    @Operation(summary = "Добавление нового вида документа")
    @PostMapping("/document/header")
    public Mono<Object> saveHeaderDocument(
                        @RequestBody @Valid
                        @Parameter(description = "Вид документа лаборатории") NewHeaderDocumentDto headerDocumentDto) {
        return client.saveHeaderDocument(headerDocumentDto);
    }

    @Operation(summary = "Изменение данных вида документа")
    @PatchMapping("/document/header")
    public Mono<Object> updateHeaderDocument(
                    @RequestBody @Valid
                    @Parameter(description = "Вид документа лаборатории") UpdateHeaderDocumentDto headerDocumentDto) {
        return client.updateHeaderDocument(headerDocumentDto);
    }

    @Operation(summary = "Получение данных типов отчетных документов")
    @GetMapping("/document/header")
    public Flux<Object> getAllHeaderDocument() {
        return client.getAllHeaderDocument();
    }

    @Operation(summary = "Удаление вида отчетного документа")
    @DeleteMapping("/document/header/{id}")
    public Mono<String> deleteHeaderDocument(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор документа") Long id) {
        return client.deleteHeaderDocument(id);
    }

    @Operation(summary = "Получение данных отчетного документа")
    @GetMapping("/document/{id}")
    public Mono<Object> getDocument(@PathVariable @NotNull @Positive
                                    @Parameter(description = "Индентификатор записи в журнале задач, замечания") Long id,
                                    @RequestParam(value = "type") @NotBlank
                                    @Parameter(description = "Тип данных для поиска по индентификатору") String type) {
        return client.getDocument(id, type);
    }
    @Operation(summary = "Получение данных отчетных документов")
    @GetMapping("/document")
    public Flux<Object> getAllDocuments(
           @RequestParam(value = "equipmentId", required = false)
           @Parameter(description = "Оборудование") Long equipmentId,
           @RequestParam(value = "addressId", required = false)
           @Parameter(description = "Адрес") Long addressId,
           @RequestParam(value = "startDate", required = false)
           @Parameter(description = "Дата начала периода, за который требуется выдать документ") LocalDate startDate,
           @RequestParam(value = "endDate", required = false)
           @Parameter(description = "Дата окончания периода, за который требуется выдать документ") LocalDate endDate) {
        return client.getAllDocuments(equipmentId, addressId, startDate, endDate);
    }

    @Operation(summary = "Добавление данных нового подр")
    @GetMapping("/lab-nk/division/{id}")
    public Flux<Object> copyLaboratoryEmployee(
                                      @PathVariable @NotNull @Positive
                                      @Parameter(description = "Индентификатор структурного подразделения") Long id
                                    , @RequestParam(name = "divisionType") @NotBlank
                                      @Parameter(description = "Тип структурного подразделения") String divisionType) {
        return client.copyLaboratoryEmployee(id, divisionType);
    }

    @Operation(summary = "Получение данных сотрудника")
    @GetMapping("/lab-nk/employee/{id}")
    public Mono<Object> getLaboratoryEmployee(@PathVariable @NotNull @Positive
                                              @Parameter(description = "Индентификатор") Long id) {
        return client.getLaboratoryEmployee(id);
    }

    @Operation(summary = "Получение данных всех сотрудников")
    @GetMapping("/lab-nk/employees")
    public Flux<Object> getAllLaboratoryEmployee() {
        return client.getAllLaboratoryEmployee();
    }

    @Operation(summary = "Удаление данных сотрудника")
    @DeleteMapping("/lab-nk/employee/{id}")
    public Mono<String> deleteLaboratoryEmployee(@PathVariable @NotNull @Positive
                                                 @Parameter(description = "Индентификатор") Long id) {
        return client.deleteLaboratoryEmployee(id);
    }
}