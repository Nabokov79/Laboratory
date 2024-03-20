package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "control_point_measurements")
public class ControlPointMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_diagnosed_id",  nullable = false)
    private EquipmentDiagnosed equipmentDiagnosed;
    @OneToMany(mappedBy = "controlPointMeasurement", fetch = FetchType.LAZY)
    private Set<ControlPoint> controlPoints;
    @OneToMany(mappedBy = "controlPointMeasurement", fetch = FetchType.LAZY)
    private Set<PointDifference> pointDifferences;
}