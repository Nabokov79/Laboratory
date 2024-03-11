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
@Table(name = "acceptable_thickness")
public class AcceptableThickness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "equipment_type_id")
    private Long equipmentTypeId;
    @Column(name = "element_id")
    private Long elementId;
    @Column(name = "part_element_id")
    private Long partElementId;
    @Column(name = "diameter")
    private Integer diameter;
    @Column(name = "acceptable_min")
    private Float acceptableMin;
    @Column(name = "acceptable_percent")
    private Integer acceptablePercent;
    @Column(name = "min_hardness")
    private Integer minHardness;
    @Column(name = "max_hardness")
    private Integer maxHardness;
    @Column(name = "measurement_error")
    private Float measurementError;
}
