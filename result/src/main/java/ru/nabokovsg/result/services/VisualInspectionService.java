package ru.nabokovsg.result.services;

import ru.nabokovsg.result.dto.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.result.dto.visualInspection.VisualInspectionDto;

import java.util.List;

public interface VisualInspectionService {

    ResponseVisualInspectionDto save(VisualInspectionDto inspectionDto);

    ResponseVisualInspectionDto update(VisualInspectionDto inspectionDto);

    List<ResponseVisualInspectionDto> getAll(Long id);

    void delete(Long id);
}