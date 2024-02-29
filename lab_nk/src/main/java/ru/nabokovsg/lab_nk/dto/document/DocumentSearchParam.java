package ru.nabokovsg.lab_nk.dto.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class DocumentSearchParam {

    private Long equipmentId;
    private Long addressId;
    private LocalDate startDate;
    private LocalDate endDate;
}