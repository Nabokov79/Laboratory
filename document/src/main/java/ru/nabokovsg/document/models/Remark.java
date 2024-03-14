package ru.nabokovsg.document.models;

import jakarta.persistence.*;
import lombok.*;
import ru.nabokovsg.document.models.enums.RemarkStatus;

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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "inspector_id",  nullable = false)
    private Subscriber inspector;
    @Column(name = "verification_status")
    @Enumerated(EnumType.STRING)
    private RemarkStatus verificationStatus;
    @Column(name = "correction_status")
    @Enumerated(EnumType.STRING)
    private RemarkStatus correctionStatus;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id",  nullable = false)
    private Document document;
}