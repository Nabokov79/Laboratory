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
@Table(name = "page_title_templates")
public class PageTitleTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "header_id", referencedColumnName = "id")
    private HeaderTemplate header;
    @Column(name = "title")
    private String title;
    @Column(name = "heading")
    private String heading;
    @Column(name = "equipment")
    private String equipment;
    @Column(name = "installation_location")
    private String installationLocation;
    @Column(name = "address")
    private String address;
    @Column(name = "post")
    private String post;
    @Column(name = "employee")
    private String employee;
    @Column(name = "city")
    private String city;
}