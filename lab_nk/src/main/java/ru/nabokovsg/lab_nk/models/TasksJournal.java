package ru.nabokovsg.lab_nk.models;

import jakarta.persistence.*;
import lombok.*;
import ru.nabokovsg.lab_nk.models.enums.Status;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks_journal")
public class TasksJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "branch_id")
    private Long branchId;
    @Column(name = "branch")
    private String branch;
    @Column(name = "building_id")
    private Long buildingId;
    @Column(name = "building")
    private String building;
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "address")
    private String address;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "equipment")
    private String equipment;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tasks_journal_employees",
            joinColumns =  {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    @ToString.Exclude
    private Set<LaboratoryEmployee> employees;
    @Column(name = "task_source")
    private String taskSource;
    @Column(name = "comment")
    private String comment;
}