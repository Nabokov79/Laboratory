package ru.nabokovsg.lab_nk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.lab_nk.dto.certificate.CertificateDto;
import ru.nabokovsg.lab_nk.dto.certificate.ResponseCertificateDto;
import ru.nabokovsg.lab_nk.models.Certificate;
import ru.nabokovsg.lab_nk.models.LaboratoryEmployee;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    @Mapping(source = "certificateDto.documentType", target = "documentType")
    @Mapping(source = "certificateDto.certificateNumber", target = "certificateNumber")
    @Mapping(source = "certificateDto.controlType", target = "controlType")
    @Mapping(source = "certificateDto.level", target = "level")
    @Mapping(source = "certificateDto.startDate", target = "startDate")
    @Mapping(source = "certificateDto.endDate", target = "endDate")
    @Mapping(source = "certificateDto.points", target = "points")
    @Mapping(source = "certificateDto.organization", target = "organization")
    @Mapping(source = "employee", target = "employee")
    @Mapping(source = "certificateDto.id", target = "id")
    Certificate mapToCertificate(CertificateDto certificateDto, LaboratoryEmployee employee);

    ResponseCertificateDto mapToFullCertificateDto(Certificate certificate);
}