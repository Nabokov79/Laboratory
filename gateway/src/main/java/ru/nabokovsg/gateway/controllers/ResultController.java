package ru.nabokovsg.gateway.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.gateway.client.ResultClient;

@RestController
@RequestMapping(
        value = "/laboratory/result",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сервис результатов выполнения работы сотрудниками лаборатории",
        description="API для работы с сервисом обработки результатов" +
                " выполнения работы сотрудниками лаборатории неразрушающего контроля")
public class ResultController {

    private final ResultClient client;
}