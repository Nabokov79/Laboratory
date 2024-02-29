package ru.nabokovsg.lab_nk.dto.document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.lab_nk.dto.headerDocument.FullHeaderDocumentDto;
import ru.nabokovsg.lab_nk.models.enums.Status;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные документа по результатам выполненной работы")
public class FullDocumentDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Вид документа")
    private FullHeaderDocumentDto headerDocument;
    @Schema(description = "Дата выполнения работы")
    private LocalDate date;
    @Schema(description = "Номер документа")
    private Integer documentNumber;
    @Schema(description = "Статус выполнения документа")
    private Status documentStatus;
    @Schema(description = "Статус выполнения схемы к документу")
    private Status drawingStatus;
    @Schema(description = "Дата передачи документа стороннему подразделению")
    private LocalDate dateTransfer;
    @Schema(description = "Номер документа, по которому был переданы результыта работы")
    private String documentTransfer;
}
