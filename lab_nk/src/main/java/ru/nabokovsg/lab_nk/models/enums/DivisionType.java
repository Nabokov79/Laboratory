package ru.nabokovsg.lab_nk.models.enums;

import java.util.Optional;

public enum DivisionType {

    ORGANIZATION,
    BRANCH,
    DEPARTMENT;

    public static Optional<DivisionType> from(String division) {
        for (DivisionType type : values()) {
            if (type.name().equalsIgnoreCase(division)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}