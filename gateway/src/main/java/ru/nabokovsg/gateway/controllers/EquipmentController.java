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
import ru.nabokovsg.gateway.client.EquipmentClient;
import ru.nabokovsg.gateway.dto.equipment_service.equipmentPassport.NewEquipmentPassportDto;
import ru.nabokovsg.gateway.dto.equipment_service.equipmentPassport.UpdateEquipmentPassportDto;
import ru.nabokovsg.gateway.dto.equipment_service.equipments.NewEquipmentDto;
import ru.nabokovsg.gateway.dto.equipment_service.equipments.UpdateEquipmentDto;
import ru.nabokovsg.gateway.dto.equipment_service.elements.NewElementDto;
import ru.nabokovsg.gateway.dto.equipment_service.elements.UpdateElementDto;
import ru.nabokovsg.gateway.dto.equipment_service.partElement.NewPartElementDto;
import ru.nabokovsg.gateway.dto.equipment_service.partElement.UpdatePartElementDto;

@RestController
@RequestMapping(
        value = "/laboratory",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Оборудование",
        description="API для работы с сервисом данных оборудования котельных, ЦТП")
public class EquipmentController {

    private final EquipmentClient client;

    @Operation(summary = "Добавление данных оборудования")
    @PostMapping("/equipments")
    public Mono<Object> saveEquipment(@RequestBody @Valid
                                      @Parameter(description = "Оборудование") NewEquipmentDto equipmentDto) {
        return client.saveEquipment(equipmentDto);
    }

    @Operation(summary = "Изменение данных оборудования")
    @PatchMapping("/equipments")
    public Mono<Object> updateEquipment(@RequestBody @Valid
                                        @Parameter(description = "Оборудование") UpdateEquipmentDto equipmentDto) {
        return client.updateEquipment(equipmentDto);
    }

    @Operation(summary = "Получить оборудование")
    @GetMapping("/equipment/{id}")
    public Mono<Object> getEquipment(@PathVariable @NotNull @Positive
                                     @Parameter(description = "Индентификатор") Long id) {
        return client.getEquipment(id);
    }

    @Operation(summary = "Получить все оборудование котельной, ЦТП")
    @GetMapping("/equipments/{id}")
    public Flux<Object> getAllEquipment(@PathVariable @NotNull @Positive
                                        @Parameter(description = "Индентификатор котельной, ЦТП") Long id) {
        return client.getAllEquipment(id);
    }

    @Operation(summary = "Удаление оборудования")
    @DeleteMapping("/equipments/{id}")
    public Mono<String> deleteEquipment(@PathVariable @NotNull @Positive
                                        @Parameter(description = "Индентификатор") Long id) {
        return client.deleteEquipment(id);
    }

    @Operation(summary = "Получить все типы оборудования")
    @GetMapping("/equipments/type")
    public Flux<Object> getAllEquipmentType() {
        return client.getAllEquipmentType();
    }

    @Operation(summary = "Добавление нового элемента")
    @PostMapping("/element")
    public Mono<Object> saveElement(@RequestBody @Valid
                                    @Parameter(description = "Элемент") NewElementDto elementDto) {
        return client.saveElement(elementDto);
    }

    @Operation(summary = "Изменение данных элемента")
    @PatchMapping("/element")
    public Mono<Object> updateElement(@RequestBody @Valid
                                      @Parameter(description = "Элемент") UpdateElementDto elementDto) {
        return client.updateElement(elementDto);
    }

    @Operation(summary = "Получить элемент")
    @GetMapping("/element/{id}")
    public Mono<Object> getElement(@PathVariable @NotNull @Positive
                                   @Parameter(description = "Индентификатор") Long id) {
        return client.getElement(id);
    }

    @Operation(summary = "Получить все элементы оборудования")
    @GetMapping("/element/all/{id}")
    public Flux<Object> getAllElement(@PathVariable @NotNull @Positive
                                      @Parameter(description = "Индентификатор оборудования") Long id) {
        return client.getAllElement(id);
    }

    @Operation(summary = "Удаление элемента")
    @DeleteMapping("/element/{id}")
    public Mono<String> deleteElement(@PathVariable @NotNull @Positive
                                                    @Parameter(description = "Индентификатор") Long id) {
        return client.deleteElement(id);
    }

    @Operation(summary = "Добавление нового подэлемента")
    @PostMapping("/element/part")
    public Mono<Object> savePartElement(@RequestBody @Valid
                                        @Parameter(description = "Подэлемент") NewPartElementDto partElementDto) {
        return client.savePartElement(partElementDto);
    }

    @Operation(summary = "Изменение данных подэлемента")
    @PatchMapping("/element/part")
    public Mono<Object> updatePartElement(@RequestBody @Valid
                                          @Parameter(description = "Подэлемент") UpdatePartElementDto partElementDto) {
        return client.updatePartElement(partElementDto);
    }

    @Operation(summary = "Получить подэлемент")
    @GetMapping("/element/part/{id}")
    public Mono<Object> getPartElement(@PathVariable @NotNull @Positive
                                       @Parameter(description = "Индентификатор") Long id) {
        return client.getPartElement(id);
    }

    @Operation(summary = "Получить все подэлементы элементы")
    @GetMapping("/element/part/all/{id}")
    public Flux<Object> getAllPartElement(
            @PathVariable @NotNull @Positive @Parameter(description = "Индентификатор элемента") Long id) {
        return client.getAllPartElement(id);
    }

    @Operation(summary = "Удаление подэлемента")
    @DeleteMapping("/element/part/{id}")
    public Mono<String> deletePartElement(@PathVariable @NotNull @Positive
                                          @Parameter(description = "Индентификатор") Long id) {
        return client.deletePartElement(id);
    }

    @Operation(summary = "Добавление данных паспорта")
    @PostMapping("/passport")
    public Mono<Object> saveEquipmentPassport(@RequestBody @Valid
                                              @Parameter(description = "Паспорт") NewEquipmentPassportDto passportDto) {
        return client.saveEquipmentPassport(passportDto);
    }

    @Operation(summary = "Изменение данных паспорта")
    @PatchMapping("/passport")
    public Mono<Object> updateEquipmentPassport(@RequestBody @Valid
                                           @Parameter(description = "Паспорт") UpdateEquipmentPassportDto passportDto) {
        return client.updateEquipmentPassport(passportDto);
    }

    @Operation(summary = "Удаление данных паспорта")
    @DeleteMapping("/passport/{id}")
    public Mono<String> deleteEquipmentPassport(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор") Long id) {
        return client.deleteEquipmentPassport(id);
    }
}