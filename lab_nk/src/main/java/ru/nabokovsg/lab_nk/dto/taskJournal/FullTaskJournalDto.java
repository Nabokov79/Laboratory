package ru.nabokovsg.lab_nk.dto.taskJournal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.nabokovsg.lab_nk.dto.employees.ShortLaboratoryEmployeeDto;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные работы в жернале задач")
public class FullTaskJournalDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Дата выполнения работы")
    private LocalDate date;
    @Schema(description = "Филиал организации")
    private String branch;
    @Schema(description = "Котельная, ЦТП")
    private String building;
    @Schema(description = "Адрес места проведения работы")
    private String address;
    @Schema(description = "Оборудование")
    private String equipment;
    @Schema(description = "Основание для проведения работы")
    private String taskSource;
    @Schema(description = "Сотрудники выполнившие работу")
    private Set<ShortLaboratoryEmployeeDto> employees;
    @Schema(description = "Комментарий к задачи")
    private String comment;
}
