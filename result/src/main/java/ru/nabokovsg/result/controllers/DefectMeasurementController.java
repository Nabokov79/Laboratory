package ru.nabokovsg.result.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.result.services.DefectMeasurementService;

@RestController
@RequestMapping(
        value = "/measurement/defect",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные измерений дефектов элементов, подэлементов оборудования",
        description="API для работы с данными измерений дефектов элементов, подэлементов оборудования")
public class DefectMeasurementController {

    private final DefectMeasurementService service;
}