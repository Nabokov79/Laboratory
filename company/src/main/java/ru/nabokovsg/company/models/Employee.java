package ru.nabokovsg.company.models;

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
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "patronymic", nullable = false)
    private String patronymic;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "post", nullable = false)
    private String post;
    @OneToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;
    @OneToOne
    @JoinColumn(name = "place_work_id", referencedColumnName = "id")
    private PlaceWork placeWork;
}