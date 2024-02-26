package ru.nabokovsg.lab_nk.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.lab_nk.client.dto.ShortEmployeeDto;
import ru.nabokovsg.lab_nk.dto.employees.DivisionDataDto;
import ru.nabokovsg.lab_nk.models.enums.DivisionType;

import java.util.List;

@Service
public class LadNKClient extends CompanyClient{

    private static final String DELIMITER = "/";
    private static final String API_PREFIX_EMPLOYEE = "/employee";

    public LadNKClient(@Value("${company-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public List<ShortEmployeeDto> getAllEmployee(DivisionDataDto divisionDataDto) {
        return getAll(String.join(DELIMITER,  API_PREFIX_EMPLOYEE, "all",  getDivisionId(divisionDataDto))
                , "divisionType", getDivisionType(divisionDataDto));
    }

    private String getDivisionId(DivisionDataDto divisionDataDto) {
        Long id = null;
        if (divisionDataDto.getOrganizationId() != null) {
            id = divisionDataDto.getOrganizationId();
        }
        if (divisionDataDto.getBranchId() != null) {
            id = divisionDataDto.getBranchId();
        }
        if (divisionDataDto.getDepartmentId() != null) {
            id = divisionDataDto.getDepartmentId();
        }
        return String.valueOf(id);
    }

    private String getDivisionType(DivisionDataDto divisionDataDto) {
        DivisionType divisionType = null;
        if (divisionDataDto.getOrganizationId() != null) {
            divisionType = DivisionType.ORGANIZATION;
        }
        if (divisionDataDto.getBranchId() != null) {
            divisionType = DivisionType.BRANCH;
        }
        if (divisionDataDto.getDepartmentId() != null) {
            divisionType = DivisionType.DEPARTMENT;
        }
        return String.valueOf(divisionType);
    }
}