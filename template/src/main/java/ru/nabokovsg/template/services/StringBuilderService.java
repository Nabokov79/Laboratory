package ru.nabokovsg.template.services;

import ru.nabokovsg.template.client.dto.*;
import ru.nabokovsg.template.dto.subsection.DivisionDataDto;

import java.util.List;

public interface StringBuilderService {

    String buildFromLicense(List<LicenseDto> licenses);

    String buildFromContacts(DivisionContact contact);

    String buildFromAddress(AddressDto address);

    String buildFromEmployeeInitials(LaboratoryEmployeeDto employee);

    String buildFromDivision(DivisionDto division, DivisionDataDto dataDto);

    String buildFromDocumentation(DocumentationDto documentations);

    String buildFromEmployeeCertificate(LaboratoryEmployeeDto employee);
}