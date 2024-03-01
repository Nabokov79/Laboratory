package ru.nabokovsg.gateway.dto.labNk_service.taskJournal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления работы в журнал задач")
public class NewTaskJournalDto {

    @Schema(description = "Дата выполнения работы")
    @NotNull(message = "date should not be null")
    private LocalDate date;
    @Schema(description = "Индентификатор филиала")
    @NotNull(message = "branch id should not be null")
    @Positive(message = "branch id can only be positive")
    private Long branchId;
    @Schema(description = "Индентификатор котельной, цтп")
    @NotNull(message = "buildingI id should not be null")
    @Positive(message = "buildingI id can only be positive")
    private Long buildingId;
    @Schema(description = "Индентификатор адреса места проведения работы")
    @NotNull(message = "address id should not be null")
    @Positive(message = "address id can only be positive")
    private Long addressId;
    @Schema(description = "Индентификатор оборудования")
    @NotNull(message = "equipment id should not be null")
    @Positive(message = "equipment id can only be positive")
    private Long equipmentId;
    @Schema(description = "Индентификаторы сотрудников")
    @NotNull(message = "employees ids should not be null")
    private List<Long> employeesId;
    @Schema(description = "Основание для проведения работы")
    @NotBlank(message = "taskSource should not be blank")
    private String taskSource;
    @Schema(description = "Комментарий к задачи")
    private String comment;
}