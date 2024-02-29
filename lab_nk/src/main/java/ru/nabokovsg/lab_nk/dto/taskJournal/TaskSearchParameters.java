package ru.nabokovsg.lab_nk.dto.taskJournal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Параметры поиска задачи на выполнения работы")
public class TaskSearchParameters {

    private Long employeeId;
    private String status;
    private LocalDate startPeriod;
    private LocalDate endPeriod;
}