package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.result.services.DefectRepairService;

@RestController
@RequestMapping(
        value = "/measurement/repair",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные выполненных ремонтов дефектов элементов, подэлементов оборудования",
        description="API для работы с данными выполненных ремонтов дефектов элементов, подэлементов оборудования")
public class DefectRepairController {

    private final DefectRepairService service;
}