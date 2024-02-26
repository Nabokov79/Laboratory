package ru.nabokovsg.gateway.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.gateway.client.CollectorClient;

@RestController
@RequestMapping(
        value = "/laboratory/collector",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сервис сборки документа",
        description="API для работы с сервисом сборки документа")
public class CollectorController {

    private final CollectorClient client;
}