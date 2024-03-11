package ru.nabokovsg.template.models.enums;

import java.util.Optional;

public enum TemplateType {

    PROTOCOL,
    SECTION,
    PROTOCOL_REPORT;

    public static Optional<TemplateType> from(String templateType) {
        for (TemplateType type : values()) {
            if (type.name().equalsIgnoreCase(templateType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}