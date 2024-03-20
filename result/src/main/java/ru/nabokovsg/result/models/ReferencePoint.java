package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reference_points")
public class ReferencePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_diagnosed_id",  nullable = false)
    private EquipmentDiagnosed equipmentDiagnosed;
    @Column(name = "place_number")
    private Integer placeNumber;
    @Column(name = "calculated_height")
    private Integer calculatedHeight;
    @Column(name = "deviation")
    private Integer deviation;
    @Column(name = "precipitation")
    private Integer precipitation;
    @OneToMany(mappedBy = "referencePoint", fetch = FetchType.LAZY)
    private List<DeviationYear> deviationYeas;
    @Column(name = "acceptable_value")
    private Boolean acceptableValue;

    @Override
    public String toString() {
        return "ReferencePoint{" +
                "id=" + id +
                ", equipmentDiagnosed=" + equipmentDiagnosed +
                ", placeNumber=" + placeNumber +
                ", calculatedHeight=" + calculatedHeight +
                ", deviation=" + deviation +
                ", precipitation=" + precipitation +
                ", deviationYeas=" + deviationYeas +
                ", acceptableValue=" + acceptableValue +
                '}';
    }
}