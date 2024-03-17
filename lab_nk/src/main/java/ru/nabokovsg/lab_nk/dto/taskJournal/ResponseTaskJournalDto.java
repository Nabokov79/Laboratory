package ru.nabokovsg.lab_nk.dto.taskJournal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.nabokovsg.lab_nk.dto.employees.ShortResponseLaboratoryEmployeeDto;
import ru.nabokovsg.lab_nk.dto.headerDocument.ResponseHeaderDocumentDto;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные работы в журнале задач")
public class ResponseTaskJournalDto {

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
    @Schema(description = "Индентификатор типа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Индентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Наименование оборудования")
    private String equipment;
    @Schema(description = "Старое или новое оборудование(относиться к бакам-аккумуляторам для рассчета данных геодезии)")
    private Boolean equipmentOld;
    @Schema(description = "Основание для проведения работы")
    private String taskSource;
    @Schema(description = "Сотрудники выполнившие работу")
    private Set<ShortResponseLaboratoryEmployeeDto> employees;
    @Schema(description = "Руководитель работ")
    private ShortResponseLaboratoryEmployeeDto chief;
    @Schema(description = "Комментарий к задаче")
    private String comment;
    @Schema(description = "Тип документа по результатам выполнения работы")
    private ResponseHeaderDocumentDto headerDocument;
}