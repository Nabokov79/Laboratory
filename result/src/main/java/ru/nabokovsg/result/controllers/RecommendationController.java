package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.result.dto.recommendation.FullRecommendationDto;
import ru.nabokovsg.result.dto.recommendation.RecommendationDto;
import ru.nabokovsg.result.services.RecommendationService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/recommendation",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Рекомендация по устранению дефектов, замечаний",
        description="API для работы с данными рекомендациями по устранению дефектов, замечаний")
public class RecommendationController {

    private final RecommendationService service;

    @Operation(summary = "Новая рекомендация для раздела отчета")
    @PostMapping
    public ResponseEntity<FullRecommendationDto> save(
                                   @RequestBody @Parameter(name = "Рекомендация") RecommendationDto recommendationDto) {
        return ResponseEntity.ok().body(service.save(recommendationDto));
    }

    @Operation(summary = "Изменение рекомендации")
    @PatchMapping
    public ResponseEntity<FullRecommendationDto> update(
                                  @RequestBody @Parameter(name = "Рекомендация") RecommendationDto recommendationDto) {
        return ResponseEntity.ok().body(service.update(recommendationDto));
    }

    @Operation(summary = "Получить рекомендации по типу объекта")
    @GetMapping("/{id}")
    public ResponseEntity<List<FullRecommendationDto>> getAll(
                                         @PathVariable @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить рекомендацию")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Рекомендация успешно удалена.");
    }
}