package ru.nabokovsg.lab_nk.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.lab_nk.models.enums.TypeDocument;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "header_documents")
public class HeaderDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "heading")
    private String heading;
    @Column(name = "type_document")
    @Enumerated(EnumType.STRING)
    private TypeDocument typeDocument;
}