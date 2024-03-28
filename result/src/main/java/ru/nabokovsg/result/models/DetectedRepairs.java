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
@Table(name = "detected_repairs")
public class DetectedRepairs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "element_id")
    private Long elementId;
    @Column(name = "part_element_id")
    private Long partElementId;
    @Column(name = "repair_id")
    private Long repairId;
    @Column(name = "repair_name")
    private String repairName;
    @OneToMany(mappedBy = "repair", fetch = FetchType.LAZY)
    private Set<ParameterMeasurement> parametersMeasurements;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "visual_measuring_survey_id",  nullable = false)
    private VisualMeasuringSurvey visualMeasuringSurvey;
}