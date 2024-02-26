package ru.nabokovsg.lab_nk.dto.measuringTool;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class RequestParameters {

    private String toll;
    private String model;
    private String workNumber;
    private LocalDate manufacturing;
    private LocalDate exploitation;
    private String manufacturer;
    private String organization;
    private String controlType;
    private Long employeeId;
}