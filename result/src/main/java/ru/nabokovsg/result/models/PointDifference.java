package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.*;
import ru.nabokovsg.result.models.enums.GeodesicPointType;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "points_difference")
public class PointDifference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private GeodesicPointType geodesicPointType;
    @Column(name = "first_place_number")
    private Integer firstPlaceNumber;
    @Column(name = "second_place_number")
    private Integer secondPlaceNumber;
    @Column(name = "difference")
    private Integer difference;
    @Column(name = "acceptable_deviation")
    private Boolean acceptableDeviation;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id",  nullable = false)
    private ControlPointMeasurement controlPointMeasurement;

    @Override
    public String toString() {
        return "PointDifference{" +
                "id=" + id +
                ", geodesicPointType=" + geodesicPointType +
                ", firstPlaceNumber=" + firstPlaceNumber +
                ", secondPlaceNumber=" + secondPlaceNumber +
                ", difference=" + difference +
                ", acceptableDeviation=" + acceptableDeviation +
                ", controlPointMeasurement=" + controlPointMeasurement +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointDifference that = (PointDifference) o;
        return Objects.equals(id, that.id) && geodesicPointType == that.geodesicPointType && Objects.equals(firstPlaceNumber, that.firstPlaceNumber) && Objects.equals(secondPlaceNumber, that.secondPlaceNumber) && Objects.equals(difference, that.difference) && Objects.equals(acceptableDeviation, that.acceptableDeviation) && Objects.equals(controlPointMeasurement, that.controlPointMeasurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, geodesicPointType, firstPlaceNumber, secondPlaceNumber, difference, acceptableDeviation, controlPointMeasurement);
    }
}
