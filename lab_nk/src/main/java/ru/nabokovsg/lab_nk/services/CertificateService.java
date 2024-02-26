package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.certificate.CertificateDto;
import ru.nabokovsg.lab_nk.dto.certificate.FullCertificateDto;
import java.util.List;

public interface CertificateService {

    FullCertificateDto save(CertificateDto certificateDto);

    FullCertificateDto update(CertificateDto certificateDto);

    List<FullCertificateDto> getAll(Long employeeId);

    void delete(Long id);
}