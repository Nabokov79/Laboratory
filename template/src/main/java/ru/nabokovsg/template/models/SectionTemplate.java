package ru.nabokovsg.template.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "section_templates")
public class SectionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "section_name")
    private String sectionName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_subsection_templates",
            joinColumns = {@JoinColumn(name = "section_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsection_id")})
    @ToString.Exclude
    private List<SubsectionTemplate> subsections;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_protocol_templates",
            joinColumns = {@JoinColumn(name = "section_id")},
            inverseJoinColumns = {@JoinColumn(name = "protocol_id")})
    @ToString.Exclude
    private List<ProtocolReportTemplate> protocols;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id",  nullable = false)
    private ReportTemplate report;
}