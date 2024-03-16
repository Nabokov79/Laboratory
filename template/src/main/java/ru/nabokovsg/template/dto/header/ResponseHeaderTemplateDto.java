package ru.nabokovsg.template.dto.header;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.template.models.DivisionData;
import ru.nabokovsg.template.models.enums.TypeDocument;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные заголовка протокола, заключения, загоовка титульного листа отчета")
public class ResponseHeaderTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    private TypeDocument typeDocument;
    @Schema(description = "Данные организации")
    private DivisionData organization;
    @Schema(description = "Данные филиала")
    private DivisionData branch;
    @Schema(description = "Данные подразделения")
    private DivisionData department;
    @Schema(description = "Данные района теплоснабжения")
    private DivisionData heatSupplyArea;
    @Schema(description = "Данные участка теплоснабжения")
    private DivisionData exploitationRegion;
}