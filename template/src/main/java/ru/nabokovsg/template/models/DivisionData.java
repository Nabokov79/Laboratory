package ru.nabokovsg.template.models;

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
@Table(name = "division_data")
public class DivisionData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "division_name")
    private String divisionName;
    @Column(name = "address")
    private String address;
    @Column(name = "license")
    private String license;
    @Column(name = "contact")
    private String contact;
}