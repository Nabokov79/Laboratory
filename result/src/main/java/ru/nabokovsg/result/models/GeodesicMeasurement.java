package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "geodesic_measurements")
public class GeodesicMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_diagnosed_id",  nullable = false)
    private EquipmentDiagnosed equipmentDiagnosed;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "number_measurement_location")
    private Integer numberMeasurementLocation;
    @Column(name = "reference_point_value")
    private Integer referencePointValue;
    @Column(name = "control_point_value")
    private Integer controlPointValue;
    @Column(name = "transition_value")
    private Integer transitionValue;

    @Override
    public String toString() {
        return "GeodesicMeasurement{" +
                "id=" + id +
                ", sequentialNumber=" + sequentialNumber +
                ", numberMeasurementLocation=" + numberMeasurementLocation +
                ", referencePointValue=" + referencePointValue +
                ", controlPointValue=" + controlPointValue +
                ", transitionValue=" + transitionValue +
                '}';
    }
}