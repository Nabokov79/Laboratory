package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

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
    @Column(name = "equipment_full")
    private Boolean full;
    @Column(name = "equipment_old")
    private Boolean equipmentOld;

    @Override
    public String toString() {
        return "EquipmentDiagnosed{" +
                "id=" + id +
                ", taskJournalId=" + taskJournalId +
                ", equipmentTypeId=" + equipmentTypeId +
                ", equipmentId=" + equipmentId +
                ", full=" + full +
                ", equipmentOld=" + equipmentOld +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentDiagnosed that = (EquipmentDiagnosed) o;
        return Objects.equals(id, that.id) && Objects.equals(taskJournalId, that.taskJournalId)
                && Objects.equals(equipmentTypeId, that.equipmentTypeId)
                && Objects.equals(equipmentId, that.equipmentId)
                && Objects.equals(full, that.full)
                && Objects.equals(equipmentOld, that.equipmentOld);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskJournalId, equipmentTypeId, equipmentId, full, equipmentOld);
    }
}