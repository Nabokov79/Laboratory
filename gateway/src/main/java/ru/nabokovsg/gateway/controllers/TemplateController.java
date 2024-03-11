package ru.nabokovsg.gateway.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.TemplateClient;
import ru.nabokovsg.gateway.dto.templates.appendices.NewAppendicesTemplateDto;
import ru.nabokovsg.gateway.dto.templates.appendices.UpdateAppendicesTemplateDto;
import ru.nabokovsg.gateway.dto.templates.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.gateway.dto.templates.conclusion.UpdateConclusionTemplateDto;
import ru.nabokovsg.gateway.dto.templates.header.NewHeaderTemplateDto;
import ru.nabokovsg.gateway.dto.templates.header.UpdateHeaderTemplateDto;
import ru.nabokovsg.gateway.dto.templates.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.gateway.dto.templates.pageTitle.UpdatePageTitleTemplateDto;
import ru.nabokovsg.gateway.dto.templates.protocols.NewProtocolTemplateDto;
import ru.nabokovsg.gateway.dto.templates.protocols.UpdateProtocolTemplateDto;
import ru.nabokovsg.gateway.dto.templates.reportProtocol.NewProtocolReportTemplateDto;
import ru.nabokovsg.gateway.dto.templates.reportProtocol.UpdateProtocolReportTemplateDto;
import ru.nabokovsg.gateway.dto.templates.sections.SectionDataTemplateDto;
import ru.nabokovsg.gateway.dto.templates.sections.UpdateSectionTemplateDto;
import ru.nabokovsg.gateway.dto.templates.subsections.NewSubsectionTemplateDto;
import ru.nabokovsg.gateway.dto.templates.subsections.UpdateSubsectionTemplateDto;
import ru.nabokovsg.gateway.dto.templates.tables.NewTableTemplateDto;
import ru.nabokovsg.gateway.dto.templates.tables.UpdateTableTemplateDto;
import ru.nabokovsg.gateway.enums.DocumentPartType;
import ru.nabokovsg.gateway.enums.TemplateType;

import java.util.List;

@RestController
@RequestMapping(
        value = "/laboratory/template",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сервис данных шаблонов документа",
        description="API для работы с сервисом данными шаблонами документов лаборатории неразрушающего контроля")
public class TemplateController {

    private final TemplateClient client;

    @Operation(summary = "Сохранить новое приложение документа")
    @PostMapping("/appendices")
    public Mono<Object> saveAppendices(@RequestBody @Valid
                                       @Parameter(name = "Приложение") NewAppendicesTemplateDto appendicesDto) {
        return client.saveAppendices(appendicesDto);
    }

    @Operation(summary = "Изменение данных приложения документа")
    @PatchMapping("/appendices")
    public Mono<Object> updateAppendices(@RequestBody @Valid
                                         @Parameter(name = "Приложение") UpdateAppendicesTemplateDto appendicesDto) {
        return client.updateAppendices(appendicesDto);
    }

    @Operation(summary = "Удалить приложение документа")
    @DeleteMapping("/appendices/{id}")
    public Mono<String> deleteAppendices(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.deleteAppendices(id);
    }

    @Operation(summary = "Данные шаблона новых заключений протокола отчета")
    @PostMapping("/conclusion/report/protocol")
    public Mono<Object> saveFromReportProtocolTemplate(
            @RequestBody @Valid @Parameter(name = "Шаблон заключений") NewConclusionTemplateDto conclusionDto) {
        return client.saveConclusion(DocumentPartType.PROTOCOL_REPORT, conclusionDto);
    }

    @Operation(summary = "Данные шаблона новых заключений протокола")
    @PostMapping("/conclusion/protocol")
    public Mono<Object> saveFromProtocolTemplate(
            @RequestBody @Valid @Parameter(name = "Шаблон заключений") NewConclusionTemplateDto conclusionDto) {
        return client.saveConclusion(DocumentPartType.PROTOCOL, conclusionDto);
    }

    @Operation(summary = "Изменение данных шаблона заключений")
    @PatchMapping("/conclusion")
    public Mono<Object> updateConclusion(
            @RequestBody @Valid @Parameter(name = "Шаблон заключений") UpdateConclusionTemplateDto conclusionDto) {
        return client.updateConclusion(conclusionDto);
    }

    @Operation(summary = "Удалить шаблон заключений")
    @DeleteMapping("/conclusion/{id}")
    public Mono<String> deleteConclusion(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.deleteConclusion(id);
    }

    @Operation(summary = "Добавление нового шаблона заголовка")
    @PostMapping("/header")
    public Mono<Object> saveHeader(
            @RequestBody @Valid @Parameter(description = "Данные шаблона заголовка") NewHeaderTemplateDto headerDto) {
        return client.saveHeader(headerDto);
    }

    @Operation(summary = "Изменение информации в шаблоне заголовка")
    @PatchMapping("/header")
    public Mono<Object> updateHeader(
           @RequestBody @Valid @Parameter(description = "Данные шаблона заголовка") UpdateHeaderTemplateDto headerDto) {
        return client.updateHeader(headerDto);
    }

    @Operation(summary = "Получить шаблон заголовка")
    @GetMapping("/header/{id}")
    public Mono<Object> getHeader(
            @PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.getHeader(id);
    }

    @Operation(summary = "Удалить шаблон заголовка")
    @DeleteMapping("/header/{id}")
    public Mono<String> deleteHeader(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.deleteHeader(id);
    }

    @Operation(summary = "Данные титульного листа")
    @PostMapping("/title-page")
    public Mono<Object> savePageTitle(
                            @RequestBody @Valid
                            @Parameter(description = "Данные Титульного листа") NewPageTitleTemplateDto pageTitleDto) {
        return client.savePageTitle(pageTitleDto);
    }

    @Operation(summary = "Изменение данных титульного листа")
    @PatchMapping("/title-page")
    public Mono<Object> updatePageTitle(
                        @RequestBody @Valid
                        @Parameter(description = "Данные Титульного листа") UpdatePageTitleTemplateDto pageTitleDto) {
        return client.updatePageTitle(pageTitleDto);
    }

    @Operation(summary = "Шаблон нового протокола")
    @PostMapping("/report/protocol")
    public Mono<Object> saveProtocolReport(
            @RequestBody @Valid
            @Parameter(name = "Шаблон протокола в составе отчета") NewProtocolReportTemplateDto protocolDto) {
        return client.saveProtocolReport(protocolDto);
    }

    @Operation(summary = "Изменение шаблона протокола")
    @PatchMapping("/report/protocol")
    public Mono<Object> updateProtocolReport(
            @RequestBody @Valid
            @Parameter(name = "Шаблон протокола в составе отчета") UpdateProtocolReportTemplateDto protocolDto) {
        return client.updateProtocolReport(protocolDto);
    }

    @Operation(summary = "Получить шаюлон протоколов")
    @GetMapping("/report/protocol/{id}")
    public Mono<Object> getProtocolReport(@PathVariable @NotNull @Positive
                                              @Parameter(description = "Индентификатор") Long id) {
        return client.getProtocolReport(id);
    }

    @Operation(summary = "Получить краткие данные протоколов")
    @GetMapping("/report/protocol/all/section/{id}")
    public Flux<Object> getAllProtocolReport(@PathVariable @NotNull @Positive
                                                 @Parameter(description = "Индентификатор раздела") Long id) {
        return client.getAllProtocolReport(id);
    }

    @Operation(summary = "Удалить шаблон протокола")
    @DeleteMapping("/report/protocol/{id}")
    public Mono<String> deleteProtocolReport(@PathVariable @NotNull @Positive
                                             @Parameter(description = "Индентификатор") Long id) {
        return client.deleteProtocolReport(id);
    }

    @Operation(summary = "Данные нового шаблона протокола/заключения")
    @PostMapping("/protocol")
    public Mono<Object> saveProtocol(@RequestBody @Valid
                           @Parameter(description = "Шаблон протокола/заключения") NewProtocolTemplateDto protocolDto) {
        return client.saveProtocol(protocolDto);
    }

    @Operation(summary = "Данные для изменения информации в шаблоне протокола/заключения")
    @PatchMapping("/protocol")
    public Mono<Object> updateProtocol(@RequestBody @Valid
                        @Parameter(description = "Шаблон протокола/заключения") UpdateProtocolTemplateDto protocolDto) {
        return client.updateProtocol(protocolDto);
    }

    @Operation(summary = "Получить данные шаблона протокола/заключения")
    @GetMapping("/protocol/{id}")
    public Mono<Object> getProtocol(@PathVariable @NotNull @Positive
                                    @Parameter(description = "Индентификатор") Long id) {
        return client.getProtocol(id);
    }

    @Operation(summary = "Получить краткие данные шаблонов протоколов/заключений")
    @GetMapping("/protocol/all")
    public Flux<Object> getAllProtocols() {
        return client.getAllProtocols();
    }

    @Operation(summary = "Получить шаблон отчета")
    @GetMapping("/report/{id}")
    public Mono<Object> getReport(@PathVariable @NotNull @Positive
                                                 @Parameter(name = "Индентификатор") Long id) {
        return client.getReport(id);
    }

    @Operation(summary = "Добавить новые разделы")
    @PostMapping("/section")
    public Mono<Object> saveSection(@RequestBody @Valid
                               @Parameter(description = "Данные шаблона разделов") SectionDataTemplateDto sectionsDto) {
        return client.saveSection(sectionsDto);
    }

    @Operation(summary = "Изменение данных разделов")
    @PatchMapping("/section")
    public Mono<Object> updateSection(@RequestBody @Valid
                       @Parameter(description = "Данные шаблона разделов") List<UpdateSectionTemplateDto> sectionsDto) {
        return client.updateSection(sectionsDto);
    }

    @Operation(summary = "Получить раздел")
    @GetMapping("/section/{id}")
    public Mono<Object> getSection(@PathVariable @NotNull @Positive
                                       @Parameter(description = "Индентификатор") Long id) {
        return client.getSection(id);
    }

    @Operation(summary = "Получить разделы отчета")
    @GetMapping("/section/report/{id}")
    public Flux<Object> getAllSections(@PathVariable @NotNull @Positive
                                           @Parameter(description = "Индентификатор отчета") Long id) {
        return client.getAllSections(id);
    }

    @Operation(summary = "Удалить раздел")
    @DeleteMapping("/section/{id}")
    public Mono<String> deleteSection(@PathVariable @NotNull @Positive
                                       @Parameter(description = "Индентификатор") Long id) {
        return client.deleteSection(id);
    }

    @Operation(summary = "Добавление нового шаблона подраздела раздела")
    @PostMapping("/sub-section")
    public Mono<Object> saveFromSectionTemplate(
                                @RequestBody @Valid
                                @Parameter(description = "Шаблон подраздела") NewSubsectionTemplateDto subsectionDto) {
        return client.saveSubsection(TemplateType.SECTION, subsectionDto);
    }

    @Operation(summary = "Добавление нового шаблона подраздела протокола")
    @PostMapping("/sub-section/protocol")
    public Mono<Object> saveFromProtocolTemplate(
                                @RequestBody @Valid
                                @Parameter(description = "Шаблон подраздела") NewSubsectionTemplateDto subsectionDto) {
        return client.saveSubsection(TemplateType.PROTOCOL, subsectionDto);
    }

    @Operation(summary = "Добавление нового шаблона подраздела протокола отчета")
    @PostMapping("/sub-section/protocol/report")
    public Mono<Object> saveFromProtocolReportTemplate(
                                @RequestBody @Valid
                                @Parameter(description = "Шаблон подраздела") NewSubsectionTemplateDto subsectionDto) {
        return client.saveSubsection(TemplateType.PROTOCOL_REPORT, subsectionDto);
    }

    @Operation(summary = "Изменение данных шаблона подраздела")
    @PatchMapping("/sub-section")
    public Mono<Object> updateSubsection(
                            @RequestBody @Valid
                            @Parameter(description = "Шаблон подраздела") UpdateSubsectionTemplateDto subsectionDto) {
        return client.updateSubsection(subsectionDto);
    }

    @Operation(summary = "Получить данные шаблона подраздела")
    @GetMapping("/sub-section/{id}")
    public Mono<Object> getSubsection(@PathVariable @NotNull @Positive
                                      @Parameter(description = "Индентификатор") Long id) {
        return client.getSubsection(id);
    }

    @Operation(summary = "Удалить подраздел")
    @DeleteMapping("/sub-section/{id}")
    public Mono<String> deleteSubsection(@PathVariable @NotNull @Positive
                                                   @Parameter(description = "Индентификатор") Long id) {
        return client.deleteSubsection(id);
    }

    @Operation(summary = "Добавление нового шаблона таблицы")
    @PostMapping("/table")
    public Mono<Object> saveTable(@RequestBody @Valid
                                 @Parameter(description = "Данные шаблона таблицы") NewTableTemplateDto tableDto) {
        return client.saveTable(tableDto);
    }

    @Operation(summary = "Изменение информации в шаблоне таблицы")
    @PatchMapping("/table")
    public Mono<Object> updateTable(@RequestBody @Valid
                                   @Parameter(description = "Данные шаблона таблицы") UpdateTableTemplateDto tableDto) {
        return client.updateTable(tableDto);
    }

    @Operation(summary = "Получить таблицу")
    @GetMapping("/table/{id}")
    public Mono<Object> getTable(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.getTable(id);
    }

    @Operation(summary = "Удалить таблицу")
    @DeleteMapping("/table/{id}")
    public Mono<String> deleteTable(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.deleteTable(id);
    }
}