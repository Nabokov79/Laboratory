package ru.nabokovsg.lab_nk.dto.taskJournal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.lab_nk.dto.employees.ShortResponseLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.dto.headerDocument.HeaderDocumentDto;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные добавления данных: документа(document-service), дигностируемого оборудования(result-service)")
public class TasksJournalDataDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Дата выполнения работы")
    private LocalDate date;
    @Schema(description = "Адрес места проведения работы")
    private String address;
    @Schema(description = "Индентификатор типа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Индентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Оборудование")
    private String equipment;
    @Schema(description = "Старое или новое оборудование(относиться к бакам-аккумуляторам для рассчета данных геодезии)")
    private Boolean equipmentOld;
    @Schema(description = "Сотрудники выполнившие работу")
    private List<ShortResponseLaboratoryEmployeeDto> employees;
    @Schema(description = "Руководитель работ")
    private ShortResponseLaboratoryEmployeeDto chief;
    @Schema(description = "Тип документа по результатам выполнения работы")
    private HeaderDocumentDto headerDocument;
}