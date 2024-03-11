package ru.nabokovsg.template.models.enums;

import java.util.Optional;

public enum SubsectionDataType {

    DIVISION,
    DOCUMENTATION,
    CERTIFICATE,
    MEASURING_TOOL;

    public static Optional<SubsectionDataType> from(String dataType) {
        for (SubsectionDataType type : values()) {
            if (type.name().equalsIgnoreCase(dataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}