package ru.nabokovsg.company.dto.heatSupplyArea;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.divisionContact.ResponseDivisionContact;
import ru.nabokovsg.company.dto.exploitationRegion.ShortResponseExploitationRegionDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные района теплоснабжения")
public class ResponseHeatSupplyAreaDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное наименование")
    private String fullName;
    @Schema(description = "Краткое наименование")
    private String shortName;
    @Schema(description = "Контакты сотрудника райна теплоснабжения")
    private ResponseDivisionContact contact;
    @Schema(description = "Эксплуатационные участки")
    private List<ShortResponseExploitationRegionDto> exploitationRegions;
}