package ru.nabokovsg.lab_nk.dto.employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные структурного подразделения")
public class DivisionDataDto {

    private Long organizationId;
    private Long branchId;
    private Long departmentId;
}