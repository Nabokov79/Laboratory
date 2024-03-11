package ru.nabokovsg.template.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report_protocol_templates")
public class ProtocolReportTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "header_document_id")
    private Long headerDocumentId;
    @Column(name = "title")
    private String title;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "heading")
    private String heading;
    @Column(name = "user_text_after_heading")
    private String userTextAfterHeading;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "report_protocol_subsection_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsection_id")})
    @ToString.Exclude
    private List<SubsectionTemplate> subsections;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "report_protocol_table_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "table_id")})
    @ToString.Exclude
    private List<TableTemplate> tables;
    @OneToOne
    @JoinColumn(name = "conclusion_template_id", referencedColumnName = "id")
    private ConclusionTemplate conclusions;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id",  nullable = false)
    private ReportTemplate report;
}