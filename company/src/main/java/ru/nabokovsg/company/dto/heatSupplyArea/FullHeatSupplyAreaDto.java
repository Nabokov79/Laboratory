package ru.nabokovsg.company.dto.heatSupplyArea;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.divisionContact.FullDivisionContact;
import ru.nabokovsg.company.dto.exploitationRegion.ShortExploitationRegionDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные района теплоснабжения")
public class FullHeatSupplyAreaDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное наименование")
    private String fullName;
    @Schema(description = "Краткое наименование")
    private String shortName;
    @Schema(description = "Контакты сотрудника райна теплоснабжения")
    private FullDivisionContact contact;
    @Schema(description = "Эксплуатационные участки")
    private List<ShortExploitationRegionDto> exploitationRegions;
}