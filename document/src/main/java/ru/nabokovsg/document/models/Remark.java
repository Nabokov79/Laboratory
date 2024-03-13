package ru.nabokovsg.document.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "remarks")
public class Remark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "remark")
    private String remark;
    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "employee")
    private String employee;
    @Column(name = "document_corrected")
    private Boolean documentCorrected;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id",  nullable = false)
    private Document document;
}