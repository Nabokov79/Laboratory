package ru.nabokovsg.lab_nk.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.lab_nk.services.TaskJournalService;

@RestController
@RequestMapping(
        value = "/journal",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Жернал выполненных задач",
        description="API для работы с данными журнала выполненных работ")
public class TaskJournalController {

    private final TaskJournalService service;
}