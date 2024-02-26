package ru.nabokovsg.gateway.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.gateway.client.LabNKClient;

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
}