package ru.nabokovsg.document.models.enums;

import java.util.Optional;

public enum RequestDataType {

    TASK_JOURNAL,
    REMARK;

    public static Optional<RequestDataType> from(String data) {
        for (RequestDataType type : values()) {
            if (type.name().equalsIgnoreCase(data)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
