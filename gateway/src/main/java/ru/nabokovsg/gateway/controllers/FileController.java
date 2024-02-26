package ru.nabokovsg.gateway.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.gateway.client.FileClient;

@RestController
@RequestMapping(
        value = "/laboratory/doc/file",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сервис файлов документа",
        description="API для работы с файловым хранилищем документов")
public class FileController {

    private final FileClient client;
}