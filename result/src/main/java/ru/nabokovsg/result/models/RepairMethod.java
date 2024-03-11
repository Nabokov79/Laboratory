package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repair_methods")
public class RepairMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "equipment_type_id")
    private Long equipmentTypeId;
    @Column(name = "repair_name")
    private String repairName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "repair_methods_size_parameters",
            joinColumns = {@JoinColumn(name = "repair_id")},
            inverseJoinColumns = {@JoinColumn(name = "parameter_id")})
    @ToString.Exclude
    private List<SizeParameters> sizeParameters;
}