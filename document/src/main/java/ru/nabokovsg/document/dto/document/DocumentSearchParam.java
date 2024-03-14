package ru.nabokovsg.document.dto.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class DocumentSearchParam {

    private Long taskJournalId;
    private Long chiefId;
    private Long inspectorId;
    private LocalDate startDate;
    private LocalDate endDate;
}