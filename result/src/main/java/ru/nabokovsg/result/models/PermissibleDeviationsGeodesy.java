package ru.nabokovsg.result.models;

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
@Table(name = "permissible_deviations_geodesy")
public class PermissibleDeviationsGeodesy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "equipment_type_id")
    private Long equipmentTypeId;
    @Column(name = "fulls")
    private Boolean full;
    @Column(name = "old")
    private Boolean old;
    @Column(name = "acceptable_precipitation")
    private Integer acceptablePrecipitation;
    @Column(name = "max_difference_neighboring_points")
    private Integer maxDifferenceNeighboringPoints;
    @Column(name = "max_difference_diametric_points")
    private Integer maxDifferenceDiametricPoints;
    @Column(name = "measurement_error")
    private Integer measurementError;
    @Column(name = "number_measurements")
    private Integer numberMeasurements;
}