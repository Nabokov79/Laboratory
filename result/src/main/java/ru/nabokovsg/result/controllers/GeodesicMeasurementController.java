package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.result.dto.geodesic.GeodesicMeasurementDto;
import ru.nabokovsg.result.dto.geodesic.GeodeticMeasurementEquipmentDto;
import ru.nabokovsg.result.dto.geodesic.ResponseGeodesicMeasurementDto;
import ru.nabokovsg.result.services.GeodesicMeasurementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/measurement/geodesic",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные измерений при выполнении геодезической съемки(нивелировании) оборудования",
        description="API для работы с данными измерений при выполнении геодезической съемки(нивелировании) оборудования")
public class GeodesicMeasurementController {

    private final GeodesicMeasurementService service;

    @Operation(summary = "Добавить данные геодезический съемки оборудования")
    @PostMapping
    public ResponseEntity<List<ResponseGeodesicMeasurementDto>> save(
            @RequestBody @Parameter(name = "Данные геодезической съемки оборудования") GeodeticMeasurementEquipmentDto measurementsDto) {
        return ResponseEntity.ok().body(service.save(measurementsDto));
    }

    @Operation(summary = "Измененить данные геодезический съемки одного места измерения")
    @PatchMapping
    public ResponseEntity<ResponseGeodesicMeasurementDto> update(
            @RequestBody @Parameter(name = "Данные геодезической съемки одного места измерения") GeodesicMeasurementDto measurementDto) {
        return ResponseEntity.ok().body(service.update(measurementDto));
    }

    @Operation(summary = "Получить данные геодезических измерений по индентификатору записи журнала задач")
    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseGeodesicMeasurementDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор записи в журнале задач") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить результаты геодезической съемки")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Геодезичекое измерение успешно удалено.");
    }
}