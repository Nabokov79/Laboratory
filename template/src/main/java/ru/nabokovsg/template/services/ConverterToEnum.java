package ru.nabokovsg.template.services;

import ru.nabokovsg.template.exceptions.BadRequestException;
import ru.nabokovsg.template.models.enums.*;

public class ConverterToEnum {

    protected DivisionType convertToDivisionType(String divisionType) {
        return DivisionType.from(divisionType)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Unknown data format divisionType=%s", divisionType))
                );
    }

    protected DocumentPartType convertToDocumentPartType(String documentPartType) {
        return DocumentPartType.from(documentPartType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown type=%s", documentPartType)));
    }

    protected TemplateType convertToTemplateType(String templateType) {
        return TemplateType.from(templateType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown type=%s", templateType)));
    }

    protected TableDataType convertToTableDataType(String tableDataType) {
        return TableDataType.from(tableDataType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown type=%s", tableDataType)));
    }

    protected SubsectionDataType convertToSubsectionDataType(String subsectionDataType) {
        return SubsectionDataType.from(subsectionDataType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown type=%s", subsectionDataType)));
    }
}
