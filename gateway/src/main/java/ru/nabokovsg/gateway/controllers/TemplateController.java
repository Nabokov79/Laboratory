package ru.nabokovsg.gateway.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.gateway.client.TemplateClient;

@RestController
@RequestMapping(
        value = "/laboratory/doc/template",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сервис данных шаблонов документа",
        description="API для работы с сервисом данными шаблонами документов лаборатории неразрушающего контроля")
public class TemplateController {

    private final TemplateClient client;
}