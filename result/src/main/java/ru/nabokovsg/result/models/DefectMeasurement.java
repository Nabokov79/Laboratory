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
@Table(name = "defect_measurements")
public class DefectMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "element_id")
    private Long elementId;
    @Column(name = "part_element_id")
    private Long partElementId;
    @Column(name = "defect_id")
    private Long defectId;
    @Column(name = "defect_name")
    private String defectName;
    @Column(name = "not_meet_requirements")
    private Boolean notMeetRequirements;
    @Column(name = "use_to_calculate")
    private Boolean useToCalculateResidualThickness;
    @OneToMany(mappedBy = "defectMeasurement", fetch = FetchType.LAZY)
    private Set<ParameterMeasurement> parametersMeasurements;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "visual_measuring_survey_id",  nullable = false)
    private VisualMeasuringSurvey visualMeasuringSurvey;
}