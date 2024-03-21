package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "control_points")
public class ControlPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "place_number")
    private Integer placeNumber;
    @Column(name = "calculated_height")
    private Integer calculatedHeight;
    @Column(name = "deviation")
    private Integer deviation;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id",  nullable = false)
    private ControlPointMeasurement controlPointMeasurement;

    @Override
    public String toString() {
        return "ControlPoint{" +
                "id=" + id +
                ", placeNumber=" + placeNumber +
                ", calculatedHeight=" + calculatedHeight +
                ", deviation=" + deviation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlPoint point = (ControlPoint) o;
        return Objects.equals(id, point.id) && Objects.equals(placeNumber, point.placeNumber) && Objects.equals(calculatedHeight, point.calculatedHeight) && Objects.equals(deviation, point.deviation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placeNumber, calculatedHeight, deviation);
    }
}