CREATE TABLE IF NOT EXISTS SIZE_PARAMETERS
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    parameters_name  VARCHAR                                 NOT NULL,
    unit_measurement VARCHAR                                 NOT NULL,
    CONSTRAINT pk_sizeParameters PRIMARY KEY (id),
    CONSTRAINT UQ_SIZE_PARAMETERS UNIQUE (parameters_name)
);

CREATE TABLE IF NOT EXISTS DEFECTS
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id     BIGINT                                  NOT NULL,
    defect_name           VARCHAR                                 NOT NULL,
    not_meet_requirements BOOLEAN                                 NOT NULL,
    use_to_calculate      BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_defect PRIMARY KEY (id),
    CONSTRAINT UQ_DEFECTS UNIQUE (equipment_type_id, defect_name)
);

CREATE TABLE IF NOT EXISTS REPAIR_METHODS
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id BIGINT                                  NOT NULL,
    repair_name       VARCHAR                                 NOT NULL,
    CONSTRAINT pk_repairMethod PRIMARY KEY (id),
    CONSTRAINT UQ_REPAIR_METHODS UNIQUE (equipment_type_id, repair_name)
);

CREATE TABLE IF NOT EXISTS DEFECTS_SIZE_PARAMETERS
(
    defect_id    BIGINT,
    parameter_id BIGINT,
    CONSTRAINT pk_defects_of_size_parameters PRIMARY KEY (defect_id, parameter_id)
);

CREATE TABLE IF NOT EXISTS REPAIR_METHODS_SIZE_PARAMETERS
(
    repair_id    BIGINT,
    parameter_id BIGINT,
    CONSTRAINT pk_repair_methods_of_size_parameters PRIMARY KEY (repair_id, parameter_id)
);

CREATE TABLE IF NOT EXISTS RECOMMENDATIONS
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id   BIGINT                                  NOT NULL,
    recommendation_text VARCHAR                                 NOT NULL,
    CONSTRAINT pk_recommendation PRIMARY KEY (id),
    CONSTRAINT UQ_RECOMMENDATIONS UNIQUE (equipment_type_id, recommendation_text)
);

CREATE TABLE IF NOT EXISTS ACCEPTABLE_THICKNESS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id  BIGINT                                  NOT NULL,
    element_id         BIGINT                                  NOT NULL,
    part_element_id    BIGINT,
    diameter           INTEGER,
    acceptable_min     FLOAT,
    acceptable_percent INTEGER,
    min_hardness       INTEGER,
    max_hardness       INTEGER,
    measurement_error  FLOAT,
    CONSTRAINT pk_acceptableThickness PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS PERMISSIBLE_DEVIATIONS_GEODESY
(
    id                                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id                 BIGINT                                  NOT NULL,
    fulls                             BOOLEAN                                 NOT NULL,
    old                               BOOLEAN                                 NOT NULL,
    acceptable_precipitation          INTEGER                                 NOT NULL,
    max_difference_neighboring_points INTEGER                                 NOT NULL,
    max_difference_diametric_points   INTEGER                                 NOT NULL,
    measurement_error                 INTEGER                                 NOT NULL,
    number_measurements               INTEGER                                 NOT NULL,
    CONSTRAINT pk_permissibleDeviationsGeodesy PRIMARY KEY (id),
    CONSTRAINT UQ_PERMISSIBLE_DEVIATIONS_GEODESY UNIQUE (equipment_type_id, fulls, old)
);

CREATE TABLE IF NOT EXISTS ACCEPTABLE_THICKNESS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id  BIGINT                                  NOT NULL,
    element_id         BIGINT                                  NOT NULL,
    part_element_id    BIGINT,
    diameter           INTEGER,
    acceptable_min     FLOAT,
    acceptable_percent INTEGER,
    min_hardness       INTEGER,
    max_hardness       INTEGER,
    measurement_error  FLOAT,
    CONSTRAINT pk_acceptableThickness PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS EQUIPMENT_DIAGNOSED
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    task_journal_id   BIGINT                                  NOT NULL,
    equipment_type_id BIGINT                                  NOT NULL,
    equipment_id      BIGINT                                  NOT NULL,
    equipment_full    BOOLEAN,
    equipment_old     BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_equipmentDiagnosed PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS GEODESIC_MEASUREMENTS
(
    id                          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_diagnosed_id      BIGINT                                  NOT NULL,
    sequential_number           INTEGER                                 NOT NULL,
    number_measurement_location INTEGER                                 NOT NULL,
    reference_point_value       INTEGER,
    control_point_value         INTEGER                                 NOT NULL,
    transition_value            INTEGER,
    CONSTRAINT pk_geodesicMeasurement PRIMARY KEY (id),
    CONSTRAINT FK_GEODESIC_MEASUREMENTS_ON_EQUIPMENT_DIAGNOSED
        FOREIGN KEY (equipment_diagnosed_id) REFERENCES equipment_diagnosed (id)
);

CREATE TABLE IF NOT EXISTS CONTROL_POINT_MEASUREMENTS
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_diagnosed_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_controlPointMeasurement PRIMARY KEY (id),
    CONSTRAINT FK_CONTROL_POINTS_MEASUREMENTS_ON_EQUIPMENT_DIAGNOSED
        FOREIGN KEY (equipment_diagnosed_id) REFERENCES equipment_diagnosed (id)
);

CREATE TABLE IF NOT EXISTS CONTROL_POINTS
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    place_number      INTEGER                                 NOT NULL,
    calculated_height INTEGER                                 NOT NULL,
    deviation         INTEGER                                 NOT NULL,
    measurement_id    BIGINT                                  NOT NULL,
    CONSTRAINT pk_controlPoint PRIMARY KEY (id),
    CONSTRAINT FK_CONTROL_POINTS_ON_CONTROL_POINTS_MEASUREMENTS
        FOREIGN KEY (measurement_id) REFERENCES control_point_measurements (id)
);

CREATE TABLE IF NOT EXISTS POINTS_DIFFERENCE
(
    id                   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    type                 VARCHAR                                 NOT NULL,
    first_place_number   INTEGER                                 NOT NULL,
    second_place_number  INTEGER                                 NOT NULL,
    difference           INTEGER                                 NOT NULL,
    acceptable_deviation BOOLEAN,
    measurement_id       BIGINT                                  NOT NULL,
    CONSTRAINT pk_pointDifference PRIMARY KEY (id),
    CONSTRAINT FK_POINTS_DIFFERENCE_ON_CONTROL_POINTS_MEASUREMENTS
        FOREIGN KEY (measurement_id) REFERENCES control_point_measurements (id)
);

CREATE TABLE IF NOT EXISTS REFERENCE_POINTS
(
    id                       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_diagnosed_id   BIGINT                                  NOT NULL,
    place_number             INTEGER                                 NOT NULL,
    calculated_height        INTEGER                                 NOT NULL,
    deviation                INTEGER                                 NOT NULL,
    precipitation            INTEGER,
    acceptable_deviation     BOOLEAN,
    acceptable_precipitation BOOLEAN,
    CONSTRAINT pk_referencePoint PRIMARY KEY (id),
    CONSTRAINT FK_REFERENCE_POINTS_ON_EQUIPMENT_DIAGNOSED
        FOREIGN KEY (equipment_diagnosed_id) REFERENCES equipment_diagnosed (id)
);

CREATE TABLE IF NOT EXISTS DEVIATION_YEARS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    reference_point_id BIGINT                                  NOT NULL,
    year               INTEGER                                 NOT NULL,
    deviation          INTEGER                                 NOT NULL,
    CONSTRAINT pk_deviationYear PRIMARY KEY (id),
    CONSTRAINT FK_DEVIATION_YEARS_ON_REFERENCE_POINTS FOREIGN KEY (reference_point_id) REFERENCES reference_points (id)
);