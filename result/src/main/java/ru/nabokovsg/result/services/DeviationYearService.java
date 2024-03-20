package ru.nabokovsg.result.services;

import ru.nabokovsg.result.models.ReferencePoint;

import java.util.List;

public interface DeviationYearService {

    void save(List<ReferencePoint> points, int year);

    void update(List<ReferencePoint> points, int year);
}