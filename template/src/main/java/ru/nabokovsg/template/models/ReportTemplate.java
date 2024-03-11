package ru.nabokovsg.template.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report_templates")
public class ReportTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "header_document_id")
    private Long headerDocumentId;
    @Column(name = "equipment_type_id")
    private Long equipmentTypeId;
    @OneToOne
    @JoinColumn(name = "page_title_id", referencedColumnName = "id")
    private PageTitleTemplate pageTitle;
    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY)
    private Set<SectionTemplate> sections;
    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY)
    private Set<AppendicesTemplate> appendices;
}