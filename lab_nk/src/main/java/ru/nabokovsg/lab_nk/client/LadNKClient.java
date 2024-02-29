package ru.nabokovsg.lab_nk.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.client.dto.FullBranchDto;
import ru.nabokovsg.lab_nk.client.dto.FullEquipmentDto;
import ru.nabokovsg.lab_nk.client.dto.ShortEmployeeDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LadNKClient {

    private final CompanyClient companyClient;
    private final EquipmentClient equipmentClient;
    private static final String DELIMITER = "/";
    private static final String API_PREFIX_EMPLOYEE = "/employee";
    private static final String API_PREFIX_BUILDING = "/building";
    private static final String API_PREFIX_EQUIPMENT = "/equipments";

    public List<ShortEmployeeDto> getAllEmployee(Long id, String divisionType) {
        return companyClient.getAllEmployees(String.join(DELIMITER, API_PREFIX_EMPLOYEE,
                "all", String.valueOf(id)), "divisionType", divisionType);
    }

    public FullBranchDto getBranch(Long id) {
        return companyClient.getBranch(String.join(DELIMITER, API_PREFIX_BUILDING, String.valueOf(id)));
    }

    public FullEquipmentDto getEquipment(Long id) {
        return equipmentClient.getEquipment(String.join(DELIMITER, API_PREFIX_EQUIPMENT, String.valueOf(id)));
    }
}