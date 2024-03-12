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
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id     BIGINT                                  NOT NULL,
    repair_name VARCHAR                                 NOT NULL,
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