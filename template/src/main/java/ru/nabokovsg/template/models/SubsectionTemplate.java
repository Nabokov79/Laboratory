package ru.nabokovsg.template.models;

import jakarta.persistence.*;
import lombok.*;
import ru.nabokovsg.template.models.enums.TypeDocument;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subsection_templates")
public class SubsectionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "subsection_name")
    private String subsectionName;
    @Column(name = "user_text")
    private String userText;
    @Column(name = "sequential_number_visible")
    private boolean sequentialNumberVisible;
    @Column(name = "certificate_employee")
    private String certificateEmployee;
    @Column(name = "division_data")
    private String divisionData;
    @Column(name = "type_document")
    @Enumerated(EnumType.STRING)
    private TypeDocument typeDocument;
    @Column(name = "protocol_conclusion")
    private boolean protocolConclusion;
    @Column(name = "auto_table")
    private boolean autoTable;
    @OneToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private TableTemplate table;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subsection_documentation_templates",
            joinColumns = {@JoinColumn(name = "subsection_id")},
            inverseJoinColumns = {@JoinColumn(name = "documentation_id")})
    @ToString.Exclude
    private List<DocumentationTemplate> documentation;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subsection_measuring_tools_templates",
            joinColumns = {@JoinColumn(name = "subsection_id")},
            inverseJoinColumns = {@JoinColumn(name = "tool_id")})
    @ToString.Exclude
    private List<MeasuringToolTemplate> measuringTools;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "protocol_id",  nullable = false)
    private ProtocolTemplate protocol;
}