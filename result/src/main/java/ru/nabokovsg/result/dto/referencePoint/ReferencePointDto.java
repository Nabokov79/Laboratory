package ru.nabokovsg.result.dto.referencePoint;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.result.dto.deviationYeas.DeviationYearDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные расчетов измерений реперов(геодезия)")
public class ReferencePointDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Номер репера")
    private Integer placeNumber;
    @Schema(description = "Рассчитанныя высота измерения")
    private Integer calculatedHeight;
    @Schema(description = "Отклонение от минимальной рассчитанной высоты измерения")
    private Integer deviation;
    @Schema(description = "Рассчитанное значение осадки")
    private Integer precipitation;
    @Schema(description = "Значения отклонений за предыдущие периоды")
    private List<DeviationYearDto> deviationYeas;
    @Schema(description = "Допустимость значение отклонения")
    private Boolean acceptableDeviation;
    @Schema(description = "Допустимое значение осадки")
    private Boolean acceptablePrecipitation;
}
