package ru.nabokovsg.equipment.models;

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
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="equipment_type_id", nullable=false)
    private EquipmentType equipmentType;
    @Column(name = "building_id")
    private Long buildingId;
    @Column(name = "equipment_name")
    private String equipmentName;
    @Column(name = "stationary_number")
    private Integer stationaryNumber;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "old")
    private Boolean old;
    @Column(name = "model")
    private String model;
    @Column(name = "height")
    private Integer height;
    @Column(name = "length")
    private Integer length;
    @Column(name = "width")
    private Integer width;
    @Column(name = "diameter")
    private Integer diameter;
    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY)
    private Set<Element> elements;
    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY)
    private Set<EquipmentPassport> passport;
}