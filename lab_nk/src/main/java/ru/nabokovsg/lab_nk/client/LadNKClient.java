package ru.nabokovsg.lab_nk.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.client.dto.BranchDto;
import ru.nabokovsg.lab_nk.client.dto.EquipmentDto;
import ru.nabokovsg.lab_nk.client.dto.ShortEmployeeDto;
import ru.nabokovsg.lab_nk.dto.taskJournal.TasksJournalDataDto;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LadNKClient {

    private final CompanyClient companyClient;
    private final EquipmentClient equipmentClient;
    private final DocumentClient documentClient;

    private final ResultClient resultClient;
    private static final String DELIMITER = "/";
    private static final String API_PREFIX_EMPLOYEE = "/employee";
    private static final String API_PREFIX_BUILDING = "/building";
    private static final String API_PREFIX_EQUIPMENT = "/equipments";
    private static final String API_PREFIX_DOCUMENT = "/document";

    private static final String API_PREFIX_EQUIPMENT_DIAGNOSED = "/equipment/diagnosed";

    public List<ShortEmployeeDto> getAllEmployee(Long id, String divisionType) {
        return companyClient.getAllEmployees(String.join(DELIMITER, API_PREFIX_EMPLOYEE,
                "all", String.valueOf(id)), "divisionType", divisionType);
    }

    public BranchDto getBranch(Long id) {
        return companyClient.getBranch(String.join(DELIMITER, API_PREFIX_BUILDING, String.valueOf(id)));
    }

    public EquipmentDto getEquipment(Long id) {
        return equipmentClient.getEquipment(String.join(DELIMITER, API_PREFIX_EQUIPMENT, String.valueOf(id)));
    }

    public void createDocumentData(TasksJournalDataDto task) {
        documentClient.createDocumentData(API_PREFIX_DOCUMENT, task);
    }

    public void createEquipmentDiagnosed(TasksJournalDataDto task) {
        resultClient.createEquipmentDiagnosed(API_PREFIX_EQUIPMENT_DIAGNOSED, task);
    }
}