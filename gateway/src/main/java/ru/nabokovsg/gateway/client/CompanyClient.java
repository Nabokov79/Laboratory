package ru.nabokovsg.gateway.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.dto.company_service.address.NewAddressDto;
import ru.nabokovsg.gateway.dto.company_service.address.UpdateAddressDto;
import ru.nabokovsg.gateway.dto.company_service.branch.NewBranchDto;
import ru.nabokovsg.gateway.dto.company_service.branch.UpdateBranchDto;
import ru.nabokovsg.gateway.dto.company_service.building.NewBuildingDto;
import ru.nabokovsg.gateway.dto.company_service.building.UpdateBuildingDto;
import ru.nabokovsg.gateway.dto.company_service.department.NewDepartmentDto;
import ru.nabokovsg.gateway.dto.company_service.department.UpdateDepartmentDto;
import ru.nabokovsg.gateway.dto.company_service.employee.NewEmployeeDto;
import ru.nabokovsg.gateway.dto.company_service.employee.UpdateEmployeeDto;
import ru.nabokovsg.gateway.dto.company_service.exploitationRegion.NewExploitationRegionDto;
import ru.nabokovsg.gateway.dto.company_service.exploitationRegion.UpdateExploitationRegionDto;
import ru.nabokovsg.gateway.dto.company_service.heatSupplyArea.NewHeatSupplyAreaDto;
import ru.nabokovsg.gateway.dto.company_service.heatSupplyArea.UpdateHeatSupplyAreaDto;
import ru.nabokovsg.gateway.dto.company_service.licenses.NewLicenseDto;
import ru.nabokovsg.gateway.dto.company_service.licenses.UpdateLicenseDto;
import ru.nabokovsg.gateway.dto.company_service.organization.NewOrganizationDto;
import ru.nabokovsg.gateway.dto.company_service.organization.UpdateOrganizationDto;

@Service
public class CompanyClient extends BaseClient {

    private static final String API_PREFIX_ADDRESS = "/address";
    private static final String API_PREFIX_ORGANIZATION = "/organization";
    private static final String API_PREFIX_BRANCH = "/branch";
    private static final String API_PREFIX_DEPARTMENT = "/department";
    private static final String API_PREFIX_HEAT_SUPPLY_AREA = "/heat/supply/area";
    private static final String API_PREFIX_EXPLOITATION_REGION = "/exploitation";
    private static final String API_PREFIX_BUILDING = "/building";
    private static final String API_PREFIX_LICENSE = "/licenses";
    private static final String API_PREFIX_EMPLOYEE = "/employee";
    private static final String DELIMITER = "/";

    @Autowired
    public CompanyClient(@Value("${company-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> saveAddress(NewAddressDto addressDto) {
        return post(API_PREFIX_ADDRESS, addressDto);
    }

    public Mono<Object> updateAddress(UpdateAddressDto addressDto) {
        return patch(API_PREFIX_ADDRESS, addressDto);
    }

    public Flux<Object> getAllAddress() {
        return getAll(API_PREFIX_ADDRESS);
    }

    public Mono<String> deleteAddress(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_ADDRESS, String.valueOf(id)));
    }

    public Mono<Object> saveOrganization(NewOrganizationDto organizationDto) {
        return post(API_PREFIX_ORGANIZATION, organizationDto);
    }

    public Mono<Object> updateOrganization(UpdateOrganizationDto organizationDto) {
        return patch(API_PREFIX_ORGANIZATION, organizationDto);
    }

    public Mono<Object> getOrganization(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_ORGANIZATION, String.valueOf(id)));
    }

    public Flux<Object> getAllOrganization() {
        return getAll(API_PREFIX_ORGANIZATION);
    }

    public Mono<String> deleteOrganization(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_ORGANIZATION, String.valueOf(id)));
    }

    public Mono<Object> saveBranch(NewBranchDto branchDto) {
        return post(API_PREFIX_BRANCH, branchDto);
    }

    public Mono<Object> updateBranch(UpdateBranchDto branchDto) {
        return patch(API_PREFIX_BRANCH, branchDto);
    }

    public Mono<Object> getBranch(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_BRANCH, String.valueOf(id)));
    }

    public Flux<Object> getAllBranch(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX_BRANCH, "all", String.valueOf(id)));
    }

    public Mono<String> deleteBranch(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_BRANCH, String.valueOf(id)));
    }

    public Mono<Object> saveDepartment(NewDepartmentDto departmentDto) {
        return post(API_PREFIX_DEPARTMENT, departmentDto);
    }

    public Mono<Object> updateDepartment(UpdateDepartmentDto departmentDto) {
        return patch(API_PREFIX_DEPARTMENT, departmentDto);
    }

    public Mono<Object> getDepartment(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_DEPARTMENT, String.valueOf(id)));
    }

    public Flux<Object> getAllDepartment(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX_DEPARTMENT, "all", String.valueOf(id)));
    }

    public Mono<String> deleteDepartment(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_BRANCH, String.valueOf(id)));
    }

    public Mono<Object> saveHeatSupplyArea(NewHeatSupplyAreaDto areaDto) {
        return post(API_PREFIX_HEAT_SUPPLY_AREA, areaDto);
    }

    public Mono<Object> updateHeatSupplyArea(UpdateHeatSupplyAreaDto areaDto) {
        return patch(API_PREFIX_HEAT_SUPPLY_AREA, areaDto);
    }

    public Mono<Object> getHeatSupplyArea(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_HEAT_SUPPLY_AREA, String.valueOf(id)));
    }

    public Flux<Object> getAllHeatSupplyArea(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX_HEAT_SUPPLY_AREA, "all", String.valueOf(id)));
    }

    public Mono<String> deleteHeatSupplyArea(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_HEAT_SUPPLY_AREA, String.valueOf(id)));
    }

    public Mono<Object> saveExploitation(NewExploitationRegionDto regionDto) {
        return post(API_PREFIX_EXPLOITATION_REGION, regionDto);
    }

    public Mono<Object> updateExploitation(UpdateExploitationRegionDto regionDto) {
        return patch(API_PREFIX_EXPLOITATION_REGION, regionDto);
    }

    public Mono<Object> getExploitation(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_EXPLOITATION_REGION, String.valueOf(id)));
    }

    public Flux<Object> getAllExploitation(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX_EXPLOITATION_REGION, "all", String.valueOf(id)));
    }

    public Mono<String> deleteExploitation(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_EXPLOITATION_REGION, String.valueOf(id)));
    }

    public Mono<Object> saveBuilding(NewBuildingDto buildingDto) {
        return post(API_PREFIX_BUILDING, buildingDto);
    }

    public Mono<Object> updateBuilding(UpdateBuildingDto buildingDto) {
        return patch(API_PREFIX_BUILDING, buildingDto);
    }

    public Mono<Object> getBuilding(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_BUILDING, String.valueOf(id)));
    }

    public Flux<Object> getAllBuildings(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX_BUILDING, "all", String.valueOf(id)));
    }

    public Mono<String> deleteBuilding(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_BUILDING, String.valueOf(id)));
    }

    public Mono<Object> saveLicense(NewLicenseDto licenseDto) {
        return post(API_PREFIX_LICENSE, licenseDto);
    }

    public Mono<Object> updateLicense(UpdateLicenseDto licenseDto) {
        return patch(API_PREFIX_LICENSE, licenseDto);
    }
    public Flux<Object> getAllLicenses(Long id, String divisionType) {
        return getAll(String.join(DELIMITER,  API_PREFIX_LICENSE, "all", String.valueOf(id))
                                                            , "divisionType", divisionType);
    }

    public Mono<String> deleteLicense(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_LICENSE, String.valueOf(id)));
    }

    public Mono<Object> saveEmployee(NewEmployeeDto employeeDto) {
        return post(API_PREFIX_EMPLOYEE, employeeDto);
    }

    public Mono<Object> updateEmployee(UpdateEmployeeDto employeeDto) {
        return patch(API_PREFIX_EMPLOYEE, employeeDto);
    }

    public Mono<Object> getEmployee(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_EMPLOYEE, String.valueOf(id)));
    }

    public Flux<Object> getAllEmployee(Long id, String divisionType) {
        return getAll(String.join(DELIMITER,  API_PREFIX_EMPLOYEE, "all", String.valueOf(id))
                , "divisionType", divisionType);
    }

    public Mono<String> deleteEmployee(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_EMPLOYEE, String.valueOf(id)));
    }
}