package ru.nabokovsg.lab_nk.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.lab_nk.models.enums.Status;

import java.time.LocalDate;

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
    @OneToOne
    @JoinColumn(name = "task_journal_id", referencedColumnName = "id")
    private TasksJournal taskJournal;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "header_document_id",  nullable = false)
    private HeaderDocument headerDocument;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "document_number")
    private Integer documentNumber;
    @Column(name = "document_status")
    @Enumerated(EnumType.STRING)
    private Status documentStatus;
    @Column(name = "drawing_status")
    @Enumerated(EnumType.STRING)
    private Status drawingStatus;
    @Column(name = "date_transfer")
    private LocalDate dateTransfer;
    @Column(name = "document_transfer")
    private String documentTransfer;
}