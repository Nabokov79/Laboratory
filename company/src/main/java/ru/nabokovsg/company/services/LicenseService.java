package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.licenses.ResponseLicenseDto;
import ru.nabokovsg.company.dto.licenses.LicenseDto;

import java.util.List;

public interface LicenseService {

    ResponseLicenseDto save(LicenseDto licenseDto);

    ResponseLicenseDto update(LicenseDto licenseDto);

    List<ResponseLicenseDto> getAll(Long id, String divisionType);

    void delete(Long id);
}