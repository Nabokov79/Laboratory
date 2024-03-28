package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "completed_repairs")
public class CompletedRepairs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "equipment_type_id")
    private Long equipmentTypeId;
    @Column(name = "repair_name")
    private String repairName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "completed_repairs_parameters",
            joinColumns = {@JoinColumn(name = "repair_id")},
            inverseJoinColumns = {@JoinColumn(name = "parameter_id")})
    @ToString.Exclude
    private List<Parameters> parameters;
}