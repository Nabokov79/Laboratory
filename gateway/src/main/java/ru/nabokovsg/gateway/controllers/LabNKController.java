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
import ru.nabokovsg.gateway.dto.labNk_service.certificates.NewCertificateDto;
import ru.nabokovsg.gateway.dto.labNk_service.certificates.UpdateCertificateDto;
import ru.nabokovsg.gateway.dto.labNk_service.documentation.NewDocumentationDto;
import ru.nabokovsg.gateway.dto.labNk_service.documentation.UpdateDocumentationDto;
import ru.nabokovsg.gateway.dto.labNk_service.headerDocument.NewHeaderDocumentDto;
import ru.nabokovsg.gateway.dto.labNk_service.headerDocument.UpdateHeaderDocumentDto;
import ru.nabokovsg.gateway.dto.labNk_service.measuringToll.NewMeasuringToolDto;
import ru.nabokovsg.gateway.dto.labNk_service.measuringToll.UpdateMeasuringToolDto;
import ru.nabokovsg.gateway.dto.labNk_service.remarks.NewRemarkDto;
import ru.nabokovsg.gateway.dto.labNk_service.remarks.UpdateRemarkDto;
import ru.nabokovsg.gateway.dto.labNk_service.taskJournal.NewTaskJournalDto;
import ru.nabokovsg.gateway.dto.labNk_service.taskJournal.UpdateTaskJournalDto;

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

    @Operation(summary = "Добавление данных нового интструмента(прибора)")
    @PostMapping("/measuring")
    public Mono<Object> saveMeasuringTool(@RequestBody @Valid
                                  @Parameter(description = "Инструмент(прибор)") NewMeasuringToolDto measuringToolDto) {
        return client.saveMeasuringTool(measuringToolDto);
    }

    @Operation(summary = "Изменение данных инструмента(прибора)")
    @PatchMapping("/measuring")
    public Mono<Object> updateMeasuringTool(@RequestBody @Valid
                              @Parameter(description = "Инструмент(прибор)") UpdateMeasuringToolDto measuringToolDto) {
        return client.updateMeasuringTool(measuringToolDto);
    }

    @Operation(summary = "Получение инструментов(приборов) по заданным параметрам")
    @GetMapping
    public Flux<Object> getAllMeasuringTools(
            @RequestParam(required = false) @Parameter(description = "Название") String toll,
            @RequestParam(required = false) @Parameter(description = "Модель") String model,
            @RequestParam(required = false) @Parameter(description = "Заводской номер") String workNumber,
            @RequestParam(required = false) @Parameter(description = "Дата изготовления") LocalDate manufacturing,
            @RequestParam(required = false) @Parameter(description = "Дата начала эксплуатации") LocalDate exploitation,
            @RequestParam(required = false) @Parameter(description = "Завод-изготовитель") String manufacturer,
            @RequestParam(required = false) @Parameter(description = "Метрологическая организация") String organization,
            @RequestParam(required = false) @Parameter(description = "Вид контроля") String controlType,
            @RequestParam(required = false) @Parameter(description = "Индентификатор сотрудника") Long employeeId) {
        return client.getAllMeasuringTools(toll, model, workNumber, manufacturing,exploitation, manufacturer,
                                           organization, controlType, employeeId);
    }

    @Operation(summary = "Удаление инструмента(прибора)")
    @DeleteMapping("/measuring/{id}")
    public Mono<String> deleteMeasuringTool(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор инструмента(прибора)") Long id) {
        return client.deleteMeasuringTool(id);
    }

    @Operation(summary = "Добавление данных нового замечания к документу или чертежу")
    @PostMapping("/remark")
    public Mono<Object> saveRemark(@RequestBody @Valid @Parameter(description = "Замечание") NewRemarkDto remarkDto) {
        return client.saveRemark(remarkDto);
    }

    @Operation(summary = "Изменение данных замечания к документу или чертежу")
    @PatchMapping("/remark")
    public Mono<Object> update(@RequestBody @Valid @Parameter(description = "Замечание") UpdateRemarkDto remarkDto) {
        return client.updateRemark(remarkDto);
    }

    @Operation(summary = "Получение замечаний к документу и чертежу")
    @GetMapping("/remarks/{id}")
    public Flux<Object> getAllRemarks(@PathVariable @NotNull @Positive
                                                      @Parameter(description = "Индентификатор сотрудника") Long id,
                                                      @RequestParam(name = "inspector") @NotNull Boolean inspector) {
        return client.getAllRemarks(id, inspector);
    }

    @Operation(summary = "Добавление данных новой задачи на выполнение работы")
    @PostMapping("/work/journal")
    public Mono<Object> saveWork(
                            @RequestBody @Valid
                            @Parameter(description = "Задача на выполнение работы") NewTaskJournalDto taskJournalDto) {
        return client.saveWork(taskJournalDto);
    }

    @Operation(summary = "Изменение данных задачи на выполнение работы")
    @PatchMapping("/work/journal")
    public Mono<Object> updateWork(
                        @RequestBody @Valid
                        @Parameter(description = "Задача на выполнение работы") UpdateTaskJournalDto taskJournalDto) {
        return client.updateWork(taskJournalDto);
    }

    @Operation(summary = "Получение задачи")
    @GetMapping("/work/journal/{id}")
    public Mono<Object> getWork(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.getWork(id);
    }

    @Operation(summary = "Получение данных задачи на выполнение работы")
    @GetMapping("/work/journal")
    public Flux<Object> getAllWorks(@RequestParam(value = "employeeId", required = false)
                                @Parameter(description = "Индентификатор сотрудника") Long employeeId
                              , @RequestParam(value = "status", required = false)
                                @Parameter(description = "Статус выполнения работы") String status
                              , @RequestParam(value = "startPeriod", required = false)
                                @Parameter(description = "Начало периода для выборки данных") LocalDate startPeriod
                              , @RequestParam(value = "endPeriod", required = false)
                                @Parameter(description = "Окончание периода для выборки данных") LocalDate endPeriod) {
        return client.getAllWorks(employeeId, status, startPeriod, endPeriod);
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping("/work/journal/{id}")
    public Mono<String> deleteWork(@PathVariable @NotNull @Positive
                                   @Parameter(description = "Индентификатор") Long id) {
        return client.deleteWork(id);
    }
}