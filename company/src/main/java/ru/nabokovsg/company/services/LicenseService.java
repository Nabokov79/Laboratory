package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.licenses.FullLicenseDto;
import ru.nabokovsg.company.dto.licenses.LicenseDto;

import java.util.List;

public interface LicenseService {

    FullLicenseDto save(LicenseDto licenseDto);

    FullLicenseDto update(LicenseDto licenseDto);

    List<FullLicenseDto> getAll(Long id, String divisionType);

    void delete(Long id);
}