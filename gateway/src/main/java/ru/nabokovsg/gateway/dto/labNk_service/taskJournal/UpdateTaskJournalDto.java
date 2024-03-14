package ru.nabokovsg.gateway.dto.labNk_service.taskJournal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения замечания к документу или чертежу")
public class UpdateTaskJournalDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
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
    @Schema(description = "Индентификатор типа документа по результатам выполнения работы")
    @NotNull(message = "header document id should not be null")
    @Positive(message = "header document id can only be positive")
    private Long headerDocumentId;
}