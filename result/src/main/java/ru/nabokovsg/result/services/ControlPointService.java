package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.GeodesicMeasurement;
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;

import java.util.List;

public interface ControlPointService {

    void save(List<GeodesicMeasurement> measurements, PermissibleDeviationsGeodesy permissibleDeviations);

    void update(List<GeodesicMeasurement> measurements, PermissibleDeviationsGeodesy permissibleDeviations);
}