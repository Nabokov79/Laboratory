package ru.nabokovsg.template.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "protocol_templates")
public class ProtocolTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "header_document_id")
    private Long headerDocumentId;
    @OneToOne
    @JoinColumn(name = "header_id", referencedColumnName = "id")
    private HeaderTemplate leftHeaderTemplate;
    @Column(name = "equipment_type_id")
    private Long equipmentTypeId;
    @Column(name = "title")
    private String title;
    @Column(name = "heading")
    private String heading;
    @OneToMany(mappedBy = "protocol", fetch = FetchType.LAZY)
    private List<SubsectionTemplate> subsections;
    @OneToMany(mappedBy = "protocol", fetch = FetchType.LAZY)
    private List<TableTemplate> tables;
    @OneToOne
    @JoinColumn(name = "conclusion_template_id", referencedColumnName = "id")
    private ConclusionTemplate conclusions;
    @OneToMany(mappedBy = "protocol", fetch = FetchType.LAZY)
    private List<AppendicesTemplate> appendices;
}