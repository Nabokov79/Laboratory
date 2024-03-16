package ru.nabokovsg.lab_nk.services;

import ru.nabokovsg.lab_nk.dto.certificate.CertificateDto;
import ru.nabokovsg.lab_nk.dto.certificate.ResponseCertificateDto;
import java.util.List;

public interface CertificateService {

    ResponseCertificateDto save(CertificateDto certificateDto);

    ResponseCertificateDto update(CertificateDto certificateDto);

    List<ResponseCertificateDto> getAll(Long id);

    void delete(Long id);
}