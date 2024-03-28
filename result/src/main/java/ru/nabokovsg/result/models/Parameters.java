package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.result.models.enums.ActionsOnParameter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parameters")
public class Parameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "parameter_name")
    private String parameterName;
    @Column(name = "unit_measurement")
    private String unitMeasurement;
    @Column(name = "use_calculate_thickness")
    private Boolean useCalculateThickness;
    @Column(name = "actions_on_parameter")
    @Enumerated(EnumType.STRING)
    private ActionsOnParameter actionsOnParameter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return id == that.id && Objects.equals(parameterName, that.parameterName)
                             && Objects.equals(unitMeasurement, that.unitMeasurement)
                             && Objects.equals(useCalculateThickness, that.useCalculateThickness)
                             && actionsOnParameter == that.actionsOnParameter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parameterName, unitMeasurement, useCalculateThickness, actionsOnParameter);
    }
}