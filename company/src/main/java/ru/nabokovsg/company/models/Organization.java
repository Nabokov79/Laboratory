package ru.nabokovsg.company.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "short_name")
    private String shortName;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<Branch> branches;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "organizations_licenses",
            joinColumns = {@JoinColumn(name = "organization_id")},
            inverseJoinColumns = {@JoinColumn(name = "license_id")})
    @ToString.Exclude
    private List<Licenses> licenses;
}