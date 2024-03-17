package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipment_diagnosed")
public class EquipmentDiagnosed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_journal_id")
    private Long taskJournalId;
    @Column(name = "equipment_type_id")
    private Long equipmentTypeId;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "full")
    private Boolean full;
    @Column(name = "equipment_old")
    private Boolean equipmentOld;
    @OneToMany(mappedBy = "equipmentDiagnosed", fetch = FetchType.LAZY)
    private List<GeodesicMeasurement> geodesicMeasurements;
    @OneToMany(mappedBy = "equipmentDiagnosed", fetch = FetchType.LAZY)
    private List<ReferencePoint> calculatingGeodesicMeasurements;
}