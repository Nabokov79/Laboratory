package ru.nabokovsg.lab_nk.models;

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
@Table(name = "remarks")
public class Remark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "remark")
    private String remark;
    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "inspector")
    private String inspector;
    @Column(name = "document_corrected")
    private Boolean documentCorrected;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id",  nullable = false)
    private Document document;
}