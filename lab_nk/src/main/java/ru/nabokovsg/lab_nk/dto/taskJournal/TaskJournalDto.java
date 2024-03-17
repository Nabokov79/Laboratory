package ru.nabokovsg.lab_nk.dto.taskJournal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения работы в журнал задач")
public class TaskJournalDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Дата выполнения работы")
    private LocalDate date;
    @Schema(description = "Индентификатор филиала")
    private Long branchId;
    @Schema(description = "Индентификатор котельной, цтп")
    private Long buildingId;
    @Schema(description = "Индентификатор адреса места проведения работы")
    private Long addressId;
    @Schema(description = "Индентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Старое или новое оборудование(относиться к бакам-аккумуляторам для рассчета данных геодезии)")
    private Boolean equipmentOld;
    @Schema(description = "Индентификаторы сотрудник руководителя работ")
    private Long chiefId;
    @Schema(description = "Индентификаторы сотрудников")
    private List<Long> employeesId;
    @Schema(description = "Основание для проведения работы")
    private String taskSource;
    @Schema(description = "Комментарий к задачи")
    private String comment;
    @Schema(description = "Индентификатор типа документа по результатам выполнения работы")
    private Long headerDocumentId;
}