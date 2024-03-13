package ru.nabokovsg.document.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Subscribers")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;
    @Column(name = "post", nullable = false)
    private String post;
    @Column(name = "initials", nullable = false)
    private String initials;
}