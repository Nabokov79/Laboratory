package ru.nabokovsg.lab_nk.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные филиала")
public class FullBranchDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String fullName;
    @Schema(description = "Краткое название")
    private String shortName;
    @Schema(description = "Подразделения")
    private List<FullExploitationRegionDto> heatSupplyAreas;
}