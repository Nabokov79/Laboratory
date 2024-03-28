package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.result.dto.defectRepair.DefectRepairDto;
import ru.nabokovsg.result.dto.defectRepair.ResponseDefectRepairDto;
import ru.nabokovsg.result.services.DetectedRepairsService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/measurement/repairs",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные измерений выполненных ремонтов обнаруженных дефектов",
        description="API для работы с данными измерений выполненных ремонтов обнаруженных дефектов")
public class DetectedRepairsController {

    private final DetectedRepairsService service;

    @Operation(summary = "Добавить данные выполненнего ремонта")
    @PostMapping
    public ResponseEntity<ResponseDefectRepairDto> save(@RequestBody
                                                        @Parameter(name = "Данные выполненнего ремонта")
                                                        DefectRepairDto defectRepairDto) {
        return ResponseEntity.ok().body(service.save(defectRepairDto));
    }

    @Operation(summary = "Измененить данные выполненнего ремонта")
    @PatchMapping
    public ResponseEntity<ResponseDefectRepairDto> update(@RequestBody
                                                          @Parameter(name = "Данные выполненнего ремонта")
                                                          DefectRepairDto defectRepairDto) {
        return ResponseEntity.ok().body(service.update(defectRepairDto));
    }

    @Operation(summary = "Получить данные выполненнего ремонта элементов оборудования")
    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseDefectRepairDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор записи в журнале задач") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить данные выполненнего ремонта")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные выполненнего ремонта успешно удалены.");
    }
}
