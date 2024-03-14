package ru.nabokovsg.document.models;

import jakarta.persistence.*;
import lombok.*;
import ru.nabokovsg.document.models.enums.DocumentStatus;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "task_journal_id")
    private Long taskJournalId;
    @Column(name = "title")
    private String title;
    @Column(name = "heading")
    private String heading;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "address")
    private String address;
    @Column(name = "equipment")
    private String equipment;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "chief_id",  nullable = false)
    private Subscriber chief;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "document_subscriber",
            joinColumns =  {@JoinColumn(name = "document_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscriber_id")})
    @ToString.Exclude
    private Set<Subscriber> inspectors;
    @Column(name = "document_number")
    private Integer documentNumber;
    @Column(name = "document_status")
    @Enumerated(EnumType.STRING)
    private DocumentStatus documentStatus;
    @Column(name = "drawing_status")
    @Enumerated(EnumType.STRING)
    private DocumentStatus drawingStatus;
}