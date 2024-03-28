package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.ParameterMeasurement;
import ru.nabokovsg.result.models.builders.ParameterMeasurementBuilder;

import java.util.Set;

public interface ParameterMeasurementService {

    Set<ParameterMeasurement> save(ParameterMeasurementBuilder builder);

    Set<ParameterMeasurement> update(ParameterMeasurementBuilder builder);
}