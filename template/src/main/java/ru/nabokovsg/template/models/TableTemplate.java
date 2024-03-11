package ru.nabokovsg.template.models;

import jakarta.persistence.*;
import lombok.*;
import ru.nabokovsg.template.models.enums.TableDataType;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_templates")
public class TableTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "table_name")
    private String tableName;
    @Column(name = "text_before_table")
    private String textBeforeTable;
    @Column(name = "text_after_table")
    private String textAfterTable;
    @Column(name = "table_data_type")
    @Enumerated(EnumType.STRING)
    private TableDataType tableDataType;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "table_columns_headers_templates",
            joinColumns = {@JoinColumn(name = "table_id")},
            inverseJoinColumns = {@JoinColumn(name = "columns_header_id")})
    @ToString.Exclude
    private List<ColumnHeaderTemplate> columnHeaders;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "protocol_id",  nullable = false)
    private ProtocolTemplate protocol;
}