package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.result.dto.hardnessMeasurement.HardnessMeasurementDataDto;
import ru.nabokovsg.result.dto.hardnessMeasurement.HardnessMeasurementDto;
import ru.nabokovsg.result.dto.hardnessMeasurement.ResponseHardnessMeasurementDto;
import ru.nabokovsg.result.services.HardnessMeasurementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/measurement/hardness",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные измерений твердости металла оборудования",
        description="API для работы с данными измерений твердости металла оборудования")
public class HardnessMeasurementController {

    private final HardnessMeasurementService service;

    @Operation(summary = "Добавить данные измерений твердости металла елементов, подэлементов оборудования")
    @PostMapping
    public ResponseEntity<List<ResponseHardnessMeasurementDto>> save(@RequestBody
                                                                 @Parameter(name = "Данные измерений твердости металла")
                                                                     HardnessMeasurementDataDto measurementsDto) {
        return ResponseEntity.ok().body(service.save(measurementsDto));
    }

    @Operation(summary = "Измененить дданные измерений твердости металла елементов, подэлементов оборудования")
    @PatchMapping
    public ResponseEntity<List<ResponseHardnessMeasurementDto>> update(@RequestBody
                                                                 @Parameter(name = "Данные измерений твердости металла")
                                                                 List<HardnessMeasurementDto> measurementsDto) {
        return ResponseEntity.ok().body(service.update(measurementsDto));
    }

    @Operation(summary = "Получить данные данные измерений твердости металла елементов, подэлементов оборудования" +
                                                                            " по индентификатору записи журнала задач")
    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseHardnessMeasurementDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор записи в журнале задач") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }
}