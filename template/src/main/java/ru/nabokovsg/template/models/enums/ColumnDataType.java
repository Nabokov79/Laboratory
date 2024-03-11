package ru.nabokovsg.template.models.enums;

import java.util.Optional;

public enum ColumnDataType {

    STRING_NUMBER,
    DATE,
    DESCRIPTION,
    DIVISION,
    NUMBER,
    ELEMENT,
    DEFECT,
    REPAIR,
    VISUAL_INSPECTION,
    PART_ELEMENT,
    DESIGN_THICKNESS,
    MEASUREMENT_RESULT,
    MAX_CORROSION,
    MIN_THICKNESS_ELEMENT,
    MIN_ALLOWABLE_THICKNESS,
    PLACE_NUMBER,
    HEIGHT,
    DEVIATION,
    PRECIPITATION,
    DIFFERENCE_NEIGHBORING_POINTS,
    DIFFERENCE_DIAMETRICAL_POINTS,
    DIAMETER,
    HARDNESS;

    public static Optional< ColumnDataType> from(String dataType) {
        for (ColumnDataType type : values()) {
            if (type.name().equalsIgnoreCase(dataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}