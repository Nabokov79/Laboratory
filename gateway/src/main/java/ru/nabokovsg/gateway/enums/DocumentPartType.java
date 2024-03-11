package ru.nabokovsg.gateway.enums;

import java.util.Optional;

public enum DocumentPartType {

    SUBSECTION,
    PROTOCOL,
    PROTOCOL_REPORT;

    public static Optional<DocumentPartType> from(String part) {
        for (DocumentPartType type : values()) {
            if (type.name().equalsIgnoreCase(part)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}