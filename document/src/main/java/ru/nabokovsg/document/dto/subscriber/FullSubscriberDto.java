package ru.nabokovsg.document.dto.subscriber;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные сотрудника, подписывающего документ")
public class FullSubscriberDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Должность")
    private String post;
    @Schema(description = "Фамилия, инициалы сотрудника")
    private String initials;
}