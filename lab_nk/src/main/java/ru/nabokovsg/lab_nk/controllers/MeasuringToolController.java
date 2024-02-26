package ru.nabokovsg.lab_nk.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.lab_nk.dto.measuringTool.FullMeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.lab_nk.dto.measuringTool.RequestParameters;
import ru.nabokovsg.lab_nk.services.MeasuringToolService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/measuring",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Измерительный инструмент(прибор)",
     description="API для работы с данными измерительных инструментов(приборов)")
public class MeasuringToolController {

    private final MeasuringToolService service;

    @Operation(summary = "Добавление данных нового интструмента(прибора)")
    @PostMapping
    public ResponseEntity<FullMeasuringToolDto> save(
                              @RequestBody
                              @Parameter(description = "Инструмент(прибор)") MeasuringToolDto measuringToolDto) {
        return ResponseEntity.ok().body(service.save(measuringToolDto));
    }

    @Operation(summary = "Изменение данных инструмента(прибора)")
    @PatchMapping
    public ResponseEntity<FullMeasuringToolDto> update(
                           @RequestBody
                           @Parameter(description = "Инструмент(прибор)") MeasuringToolDto measuringToolDto) {
        return ResponseEntity.ok().body(service.update(measuringToolDto));
    }

    @Operation(summary = "Получение инструментов(приборов) по заданным параметрам")
    @GetMapping
    public ResponseEntity<List<FullMeasuringToolDto>> getAll(
     @RequestParam(required = false) @Parameter(description = "Название") String toll,
     @RequestParam(required = false) @Parameter(description = "Модель") String model,
     @RequestParam(required = false) @Parameter(description = "Заводской номер") String workNumber,
     @RequestParam(required = false) @Parameter(description = "Дата изготовления") LocalDate manufacturing,
     @RequestParam(required = false) @Parameter(description = "Дата начала эксплуатации") LocalDate exploitation,
     @RequestParam(required = false) @Parameter(description = "Завод-изготовитель") String manufacturer,
     @RequestParam(required = false) @Parameter(description = "Метрологическая организация") String organization,
     @RequestParam(required = false) @Parameter(description = "Вид контроля") String controlType,
     @RequestParam(required = false) @Parameter(description = "Индентификатор сотрудника") Long employeeId) {
        return ResponseEntity.ok().body(service.getAll(new RequestParameters(toll, model, workNumber, manufacturing,
                                                                           exploitation, manufacturer, organization,
                                                                           controlType, employeeId)));
    }

    @Operation(summary = "Удаление инструмента(прибора)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable
                                         @Parameter(description = "Индентификатор инструмента(прибора)") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Инструмент/прибор успешно удален.");
    }
}