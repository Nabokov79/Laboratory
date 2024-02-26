package ru.nabokovsg.company.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.licenses.FullLicenseDto;
import ru.nabokovsg.company.dto.licenses.LicenseDto;
import ru.nabokovsg.company.exceptions.BadRequestException;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mappers.LicenseMapper;
import ru.nabokovsg.company.models.*;
import ru.nabokovsg.company.models.enums.DivisionType;
import ru.nabokovsg.company.repository.LicenseRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository repository;
    private final LicenseMapper mapper;
    private final EntityManager em;
    private final OrganizationService organizationService;
    private final BranchService branchService;
    private final DepartmentService departmentService;

    @Override
    public FullLicenseDto save(LicenseDto licenseDto) {
        Licenses license = Objects.requireNonNullElseGet(
                getByPredicate(licenseDto), () -> repository.save(mapper.mapToNewLicenses(licenseDto
                                                        , organizationService.getById(licenseDto.getIssuedLicenseId())))
        );
        addToDivision(licenseDto.getDivisionType(), licenseDto.getDivisionId(), license);
        return mapper.mapToFullLicenseDto(license);
    }

    @Override
    public FullLicenseDto update(LicenseDto licenseDto) {
        if (repository.existsById(licenseDto.getId())) {
            return mapper.mapToFullLicenseDto(repository.save(mapper.mapToUpdateLicenses(licenseDto
                                                      , organizationService.getById(licenseDto.getIssuedLicenseId()))));
        }
        throw new NotFoundException(String.format("License with id=%s not found for update", licenseDto.getId()));
    }

    @Override
    public List<FullLicenseDto> getAll(Long id, String divisionType) {
        Set<Licenses> licenses = new HashSet<>();
        switch (convert(divisionType)) {
            case ORGANIZATION -> licenses = repository.findAllByOrganizationId(id);
            case BRANCH -> licenses = repository.findAllByBranchId(id);
            case DEPARTMENT -> licenses = repository.findAllByDepartmentId(id);
        }
        if (licenses.isEmpty()) {
            return new ArrayList<>();
        }
        return licenses.stream().map(mapper::mapToFullLicenseDto).toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("License with id=%s not found for delete", id));
    }

    private void addToDivision(String divisionType, Long id, Licenses license) {
        switch (convert(divisionType)) {
            case ORGANIZATION -> organizationService.addLicense(id, license);
            case BRANCH -> branchService.addLicense(id, license);
            case DEPARTMENT -> departmentService.addLicense(id, license);
        }
    }

    private Licenses getByPredicate(LicenseDto licenseDto) {
        QLicenses licenses = QLicenses.licenses;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (licenseDto.getDocumentType() != null) {
            booleanBuilder.and(licenses.documentType.eq(licenseDto.getDocumentType()));
        }
        if (licenseDto.getLicenseNumber() != null) {
            booleanBuilder.and(licenses.licenseNumber.eq(licenseDto.getLicenseNumber()));
        }
        if (licenseDto.getStartData() != null) {
            booleanBuilder.and(licenses.startData.eq(licenseDto.getStartData()));
        }
        if (licenseDto.getEndData() != null) {
            booleanBuilder.and(licenses.endData.eq(licenseDto.getEndData()));
        }
        if (licenseDto.getIssuedLicenseId() != null) {
            booleanBuilder.and(licenses.issuedLicense.id.eq(licenseDto.getIssuedLicenseId()));
        }
        switch (convert(licenseDto.getDivisionType())) {
            case ORGANIZATION -> booleanBuilder.and(QOrganization.organization.id.eq(licenseDto.getDivisionId()));
            case BRANCH -> booleanBuilder.and(QBranch.branch.id.eq(licenseDto.getDivisionId()));
            case DEPARTMENT -> booleanBuilder.and(QDepartment.department.id.eq(licenseDto.getDivisionId()));
        }
        return new JPAQueryFactory(em).from(licenses)
                .select(licenses)
                .where(booleanBuilder)
                .fetchOne();
    }

    private DivisionType convert(String divisionType) {
        return DivisionType.from(divisionType)
                           .orElseThrow(() -> new BadRequestException(
                                   String.format("Unknown data format divisionType=%s", divisionType))
                           );
    }
}