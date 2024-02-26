package ru.nabokovsg.gateway.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.CompanyClient;
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

@RestController
@RequestMapping(
        value = "/laboratory",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name = "Сервис данных организации",
        description = "API для работы с сервисом данных организации, филиалов подразделений и их сотрудников")
public class CompanyController {

    private final CompanyClient client;

    @Operation(summary = "Добавление нового адреса")
    @PostMapping("/address")
    public Mono<Object> saveAddress(@RequestBody @Valid @Parameter(description = "Адрес") NewAddressDto addressDto) {
        return client.saveAddress(addressDto);
    }

    @Operation(summary = "Изменение данных адреса")
    @PatchMapping("/address")
    public Mono<Object> updateAddress(@RequestBody @Valid
                                      @Parameter(description = "Адрес") UpdateAddressDto addressDto) {
        return client.updateAddress(addressDto);
    }

    @Operation(summary = "Получение всех адресов")
    @GetMapping("/address")
    public Flux<Object> getAllAddress() {
        return client.getAllAddress();
    }

    @Operation(summary = "Удаление адреса")
    @DeleteMapping("/address/{id}")
    public Mono<String> deleteAddress(@PathVariable @NotNull @Positive
                                      @Parameter(description = "Индентификатор адреса") Long id) {
        return client.deleteAddress(id);
    }

    @Operation(summary = "Добавление данных организации")
    @PostMapping("/organization")
    public Mono<Object> saveOrganization(@RequestBody @Valid
                                         @Parameter(description = "Организация") NewOrganizationDto organizationDto) {
        return client.saveOrganization(organizationDto);
    }

    @Operation(summary = "Изменение данных организации")
    @PatchMapping("/organization")
    public Mono<Object> updateOrganization(@RequestBody @Valid
                                        @Parameter(description = "Организация") UpdateOrganizationDto organizationDto) {
        return client.updateOrganization(organizationDto);
    }

    @Operation(summary = "Получение полных данных организации")
    @GetMapping("/organization/{id}")
    public Mono<Object> getOrganization(@PathVariable @NotNull @Positive
                                        @Parameter(description = "Индентификатор") Long id) {
        return client.getOrganization(id);
    }

    @Operation(summary = "Получение кратких сведений об организациях")
    @GetMapping("/organizations")
    public  Flux<Object> getAllOrganization() {
        return client.getAllOrganization();
    }

    @Operation(summary = "Удаление данных организации")
    @DeleteMapping("/organization/{id}")
    public Mono<String> deleteOrganization(@PathVariable @NotNull @Positive
                                           @Parameter(description = "Индентификатор") Long id) {
        return client.deleteOrganization(id);
    }

    @Operation(summary = "Добавление данных филиала")
    @PostMapping("/branch")
    public Mono<Object> saveBranch(@RequestBody @Valid @Parameter(description = "Филиал") NewBranchDto branchDto) {
        return client.saveBranch(branchDto);
    }

    @Operation(summary = "Изменение данных филиала")
    @PatchMapping("/branch")
    public Mono<Object> updateBranch(@RequestBody @Valid @Parameter(description = "Филиал") UpdateBranchDto branchDto) {
        return client.updateBranch(branchDto);
    }

    @Operation(summary = "Получение данных филиала")
    @GetMapping("/branch/{id}")
    public Mono<Object> getBranch(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.getBranch(id);
    }

    @Operation(summary = "Получение сведений о филиалах")
    @GetMapping("/branches/{id}")
    public Flux<Object> getAllBranch(@PathVariable @NotNull @Positive
                                     @Parameter(description = "Индентификатор организации") Long id) {
        return client.getAllBranch(id);
    }

    @Operation(summary = "Удаление данных филиала")
    @DeleteMapping("/branch/{id}")
    public Mono<String> deleteBranch(@PathVariable @NotNull @Positive
                                     @Parameter(description = "Индентификатор") Long id) {
        return client.deleteBranch(id);
    }

    @Operation(summary = "Добавление данных подразделения филиала организации")
    @PostMapping("/department")
    public Mono<Object> saveDepartment(@RequestBody @Valid
                                       @Parameter(description = "Подразделение") NewDepartmentDto departmentDto) {
        return client.saveDepartment(departmentDto);
    }

    @Operation(summary = "Изменение данных подразделения филиала организации")
    @PatchMapping("/department")
    public Mono<Object> updateDepartment(@RequestBody @Valid
                                         @Parameter(description = "Подразделение") UpdateDepartmentDto departmentDto) {
        return client.updateDepartment(departmentDto);
    }

    @Operation(summary = "Получение данных подразделения филиала организации")
    @GetMapping("/department/{id}")
    public Mono<Object> getDepartment(@PathVariable @NotNull @Positive
                                      @Parameter(description = "Индентификатор") Long id) {
        return client.getDepartment(id);
    }

    @Operation(summary = "Получение кратких сведений о подразделении филиала")
    @GetMapping("/departments/{id}")
    public Flux<Object> getAllDepartment(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор филиала") Long id) {
        return client.getAllDepartment(id);
    }

    @Operation(summary = "Удаление данных подразделения филиала")
    @DeleteMapping("/department/{id}")
    public Mono<String> deleteDepartment(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор") Long id) {
        return client.deleteDepartment(id);
    }

    @Operation(summary = "Добавление данных района теплоснабжения")
    @PostMapping("/area")
    public Mono<Object> saveHeatSupplyArea(@RequestBody @Valid
                                        @Parameter(description = "Район теплоснабжения") NewHeatSupplyAreaDto areaDto) {
        return client.saveHeatSupplyArea(areaDto);
    }

    @Operation(summary = "Изменение данных района теплоснабжения")
    @PatchMapping("/area")
    public Mono<Object> updateHeatSupplyArea(@RequestBody @Valid
                                     @Parameter(description = "Район теплоснабжения") UpdateHeatSupplyAreaDto areaDto) {
        return client.updateHeatSupplyArea(areaDto);
    }

    @Operation(summary = "Получение данных района теплоснабжения")
    @GetMapping("/area/{id}")
    public Mono<Object> getHeatSupplyArea(@PathVariable @NotNull @Positive
                                          @Parameter(description = "Индентификатор") Long id) {
        return client.getHeatSupplyArea(id);
    }

    @Operation(summary = "Получение кратких сведений о всех района теплоснабжения")
    @GetMapping("/areas/{id}")
    public Flux<Object> getAllHeatSupplyArea(@PathVariable @NotNull @Positive
                                             @Parameter(description = "Индентификатор филиала") Long id) {
        return client.getAllHeatSupplyArea(id);
    }

    @Operation(summary = "Удаление данных района теплоснабжения")
    @DeleteMapping("/area/{id}")
    public Mono<String> deleteHeatSupplyArea(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор") Long id) {
        return client.deleteHeatSupplyArea(id);
    }

    @Operation(summary = "Добавление данных эксплуатационного участка")
    @PostMapping("/exploitation")
    public Mono<Object> saveExploitation(@RequestBody @Valid
                              @Parameter(description = "Эксплуатационный участок") NewExploitationRegionDto regionDto) {
        return client.saveExploitation(regionDto);
    }

    @Operation(summary = "Изменение данных эксплуатационного участка")
    @PatchMapping("/exploitation")
    public Mono<Object> updateExploitation(@RequestBody @Valid
                           @Parameter(description = "Эксплуатационный участок") UpdateExploitationRegionDto regionDto) {
        return client.updateExploitation(regionDto);
    }

    @Operation(summary = "Получение данных эксплуатационного участка")
    @GetMapping("/exploitation/{id}")
    public Mono<Object> getExploitation(@PathVariable @NotNull @Positive
                                        @Parameter(description = "Индентификатор") Long id) {
        return client.getExploitation(id);
    }

    @Operation(summary = "Получение кратких сведений о всех эксплуатационных участках")
    @GetMapping("/exploitations/{id}")
    public Flux<Object> getAllExploitation(@PathVariable @NotNull @Positive
                                           @Parameter(description = "Индентификатор района теплоснабжения") Long id) {
        return client.getAllExploitation(id);
    }

    @Operation(summary = "Удаление данных эксплуатационного участка")
    @DeleteMapping("/exploitation/{id}")
    public Mono<String> deleteExploitation(@PathVariable @NotNull @Positive
                                           @Parameter(description = "Индентификатор") Long id) {
        return client.deleteExploitation(id);
    }

    @Operation(summary = "Добавление новой информации о котельной(цтп)")
    @PostMapping("/building")
    public Mono<Object> saveBuilding(@RequestBody @Valid
                                                @Parameter(description = "Котельная, ЦТП") NewBuildingDto buildingDto) {
        return client.saveBuilding(buildingDto);
    }

    @Operation(summary = "Изменение данных котельной(цтп)")
    @PatchMapping("/building")
    public Mono<Object> updateBuilding(@RequestBody @Valid
                                             @Parameter(description = "Котельная, ЦТП") UpdateBuildingDto buildingDto) {
        return client.updateBuilding(buildingDto);
    }

    @Operation(summary = "Получение данных всех котельных(цтп)")
    @GetMapping("/building/{id}")
    public Mono<Object> getBuilding(@PathVariable @NotNull @Positive
                                                           @Parameter(description = "Индентификатор") Long id) {
        return client.getBuilding(id);
    }

    @Operation(summary = "Получение данных всех котельных(цтп)")
    @GetMapping("/buildings/{id}")
    public Flux<Object> getAllBuildings(@PathVariable @NotNull @Positive
                                        @Parameter(description = "Индентификатор эксплуатационного участка") Long id) {
        return client.getAllBuildings(id);
    }

    @Operation(summary = "Удаление данных котельной(цтп)")
    @DeleteMapping("/building/{id}")
    public Mono<String> deleteBuilding(@PathVariable @NotNull @Positive
                                                              @Parameter(description = "Индентификатор") Long id) {
        return client.deleteBuilding(id);
    }

    @Operation(summary = "Добавление данных лицензии/свидетельства")
    @PostMapping("/licenses")
    public Mono<Object> saveLicense(@RequestBody @Valid
                                    @Parameter(description = "Лицензия/Свидетельство") NewLicenseDto licenseDto) {
        return client.saveLicense(licenseDto);
    }

    @Operation(summary = "Изменение данных лицензии/свидетельства")
    @PatchMapping("/licenses")
    public Mono<Object> updateLicense(@RequestBody @Valid
                                      @Parameter(description = "Лицензия/Свидетельство") UpdateLicenseDto licenseDto) {
        return client.updateLicense(licenseDto);
    }

    @Operation(summary = "Получение данных всех лицензий/свидетельств")
    @GetMapping("/licenses/{id}")
    public Flux<Object> getAllLicenses(@PathVariable @NotNull @Positive
                                       @Parameter(description = "Индентификатор структурного подразделения") Long id,
                                       @RequestParam(name = "divisionType") @NotBlank
                                       @Parameter(description = "Тип структурного подразделения") String divisionType) {
        return client.getAllLicenses(id, divisionType);
    }

    @Operation(summary = "Удаление данных эксплуатационного участка")
    @DeleteMapping("/licenses/{id}")
    public Mono<String> deleteLicense(@PathVariable @NotNull @Positive
                                      @Parameter(description = "Индентификатор") Long id) {
        return client.deleteLicense(id);
    }

    @Operation(summary = "Добавление данных нового сотрудника")
    @PostMapping("/employee")
    public Mono<Object> saveEmployee(@RequestBody @Valid
                                     @Parameter(description = "Сотрудник") NewEmployeeDto employeeDto) {
        return client.saveEmployee(employeeDto);
    }

    @Operation(summary = "Изменение данных сотрудника")
    @PatchMapping("/employee")
    public Mono<Object> updateEmployee(@RequestBody @Valid
                                       @Parameter(description = "Сотрудник") UpdateEmployeeDto employeeDto) {
        return client.updateEmployee(employeeDto);
    }

    @Operation(summary = "Получение данных сотрудника")
    @GetMapping("/employee/{id}")
    public Mono<Object>getEmployee(@PathVariable @NotNull @Positive
                                   @Parameter(description = "Индентификатор") Long id) {
        return client.getEmployee(id);
    }

    @Operation(summary = "Получение данных всех сотрудников")
    @GetMapping("/employees/{id}")
    public Flux<Object> getAllEmployee(@PathVariable @NotNull @Positive
                                       @Parameter(description = "Индентификатор структурного подразделения") Long id,
                                       @RequestParam(name = "divisionType") @NotBlank
                                       @Parameter(description = "Тип структурного подразделения") String divisionType) {
        return client.getAllEmployee(id, divisionType);
    }

    @Operation(summary = "Удаление данных сотрудника")
    @DeleteMapping("/employee/{id}")
    public Mono<String> deleteEmployee(@PathVariable @NotNull @Positive
                                                 @Parameter(description = "Индентификатор") Long id) {
        return client.deleteEmployee(id);
    }
}