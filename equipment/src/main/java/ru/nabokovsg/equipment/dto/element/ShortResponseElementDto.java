package ru.nabokovsg.equipment.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.equipment.dto.standardSize.StandardSizeDto;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Краткие данные элемента оборудования")
public class ShortResponseElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Наименование элемента")
    private String elementName;
    @Schema(description = "Типоразмер")
    private StandardSizeDto standardSize;
}