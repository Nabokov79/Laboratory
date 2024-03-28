package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "defects")
public class Defect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "equipment_type_id")
    private Long equipmentTypeId;
    @Column(name = "defect_name")
    private String defectName;
    @Column(name = "not_meet_requirements")
    private Boolean notMeetRequirements;
    @Column(name = "use_to_calculate")
    private Boolean useToCalculateResidualThickness;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "defects_parameters",
            joinColumns = {@JoinColumn(name = "defect_id")},
            inverseJoinColumns = {@JoinColumn(name = "parameter_id")})
    @ToString.Exclude
    private Set<Parameters> parameters;
}