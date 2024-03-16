package ru.nabokovsg.lab_nk.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.dto.certificate.CertificateDto;
import ru.nabokovsg.lab_nk.dto.certificate.ResponseCertificateDto;
import ru.nabokovsg.lab_nk.exceptions.NotFoundException;
import ru.nabokovsg.lab_nk.mappers.CertificateMapper;
import ru.nabokovsg.lab_nk.models.Certificate;
import ru.nabokovsg.lab_nk.repository.CertificateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository repository;
    private final CertificateMapper mapper;
    private final LaboratoryEmployeeService employeeService;

    @Override
    public ResponseCertificateDto save(CertificateDto certificateDto) {
        Certificate certificate = repository.findByControlTypeAndEmployeeId(certificateDto.getControlType()
                , certificateDto.getEmployeeId());
        if (certificate == null) {
            certificate = mapper.mapToCertificate(certificateDto
                    , employeeService.getById(certificateDto.getEmployeeId()));
        }
        return mapper.mapToFullCertificateDto(repository.save(certificate));
    }

    @Override
    public ResponseCertificateDto update(CertificateDto certificateDto) {
        if (repository.existsById(certificateDto.getId())) {
            return mapper.mapToFullCertificateDto(repository.save(mapper.mapToCertificate(certificateDto
                    , employeeService.getById(certificateDto.getEmployeeId()))));
        }
        throw new NotFoundException(
                String.format("Certificate with id=%s not found for update", certificateDto.getId())
        );
    }

    @Override
    public List<ResponseCertificateDto> getAll(Long id) {
        return repository.findAllByEmployeeId(id)
                .stream()
                .map(mapper::mapToFullCertificateDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Certificate with id = %s not found for delete", id));
    }
}