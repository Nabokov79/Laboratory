package ru.nabokovsg.company.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.company.models.enums.BuildingType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "buildings")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "building_type")
    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;
    @Column(name = "login")
    private String login;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private DivisionContact contact;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id",  nullable = false)
    private ExploitationRegion exploitationRegion;
}