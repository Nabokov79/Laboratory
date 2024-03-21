package ru.nabokovsg.result.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deviation_years")
public class DeviationYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "year")
    private Integer year;
    @Column(name = "deviation")
    private Integer deviation;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "reference_point_id",  nullable = false)
    private ReferencePoint referencePoint;

    @Override
    public String toString() {
        return "DeviationYear{" +
                "id=" + id +
                ", year=" + year +
                ", deviation=" + deviation +
                ", referencePoint=" + referencePoint +
                '}';
    }
}