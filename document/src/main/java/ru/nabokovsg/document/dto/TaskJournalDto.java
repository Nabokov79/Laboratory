package ru.nabokovsg.document.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.lab_nk.dto.employees.ShortLaboratoryEmployeeDto;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные работы в жернале задач")
public class TaskJournalDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Дата выполнения работы")
    private LocalDate date;
    @Schema(description = "Адрес места проведения работы")
    private String address;
    @Schema(description = "Оборудование")
    private String equipment;
    @Schema(description = "Сотрудники выполнившие работу")
    private List<LaboratoryEmployeeDto> employees;
    @Schema(description = "Руководитель работ")
    private LaboratoryEmployeeDto chief;
}