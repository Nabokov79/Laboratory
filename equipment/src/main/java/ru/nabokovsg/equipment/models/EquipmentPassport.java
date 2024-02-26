package ru.nabokovsg.equipment.models;

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
@Table(name = "equipments_passport")
public class EquipmentPassport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "header")
    private String header;
    @Column(name = "meaning")
    private String meaning;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id",  nullable = false)
    private Equipment equipment;
}