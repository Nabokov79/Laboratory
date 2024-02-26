package ru.nabokovsg.equipment.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.equipment.dto.partElement.FullPartElementDto;
import ru.nabokovsg.equipment.dto.standardSize.StandardSizeDto;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные элемента оборудования")
public class FullElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Наименование")
    private String elementName;
    @Schema(description = "Данные подэлементов")
    private Set<FullPartElementDto> partsElement;
    @Schema(description = "Типоразмер")
    private StandardSizeDto standardSize;
}