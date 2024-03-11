package ru.nabokovsg.company.models.enums;

import java.util.Optional;

public enum BuildingType {

    BOILER_ROOM,
    CENTRAL_HEATING_POINT,
    INDIVIDUAL_HEATING_POINT;

    public static Optional<BuildingType> from(String buildingType) {
        for (BuildingType type : values()) {
            if (type.name().equalsIgnoreCase(buildingType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}