package ru.nabokovsg.template.models.enums;

import java.util.Optional;

public enum TableDataType {

    INSPECTION,
    REPAIR,
    VMCS,
    VMC,
    UTM,
    UI,
    POINTS,
    CONTROL_POINTS;

    public static Optional<TableDataType> from(String dataType) {
        for (TableDataType type : values()) {
            if (type.name().equalsIgnoreCase(dataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
