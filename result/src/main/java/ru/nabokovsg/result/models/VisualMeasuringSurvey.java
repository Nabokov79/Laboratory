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
@Table(name = "visual_measuring_surveys")
public class VisualMeasuringSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_diagnosed_id",  nullable = false)
    private EquipmentDiagnosed equipmentDiagnosed;
    @OneToMany(mappedBy = "visualMeasuringSurvey", fetch = FetchType.LAZY)
    private Set<DefectMeasurement> defectMeasurements;
    @OneToMany(mappedBy = "visualMeasuringSurvey", fetch = FetchType.LAZY)
    private Set<DetectedRepairs> detectedRepairs;
    @OneToMany(mappedBy = "visualMeasuringSurvey", fetch = FetchType.LAZY)
    private Set<VisualInspection> visualInspections;
}
