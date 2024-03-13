package ru.nabokovsg.document.dto.document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.document.dto.subscriber.FullSubscriberDto;
import ru.nabokovsg.document.models.enums.DocumentStatus;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные документа по результатам выполненной работы")
public class FullDocumentDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String heading;
    @Schema(description = "Дата выполнения работы")
    private LocalDate date;
    @Schema(description = "Номер документа")
    private Integer documentNumber;
    @Schema(description = "Руководитель работ")
    private FullSubscriberDto chief;
    @Schema(description = "Сотрудники, выполнившие обследование")
    private List<FullSubscriberDto> inspectors;
    @Schema(description = "Статус выполнения документа")
    private DocumentStatus documentStatus;
    @Schema(description = "Статус выполнения схемы к документу")
    private DocumentStatus drawingStatus;
}
