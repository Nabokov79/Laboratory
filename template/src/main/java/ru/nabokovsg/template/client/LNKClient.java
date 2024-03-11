package ru.nabokovsg.template.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.template.client.dto.*;

import java.util.List;

@Service
public class LNKClient extends Client {

    private static final String DELIMITER = "/";
    private static final String API_PREFIX_HEADER = "/document/header";
    private static final String API_PREFIX_EMPLOYEE = "/employee";
    private static final String API_PREFIX_DOCUMENTATION = "/documentation";
    private static final String API_PREFIX_MEASURING = "/measuring";

    @Autowired
    public LNKClient(@Value("${labNk-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public HeaderDocumentDto getHeaderDocument(Long id) {
        return getHeaderDocument(String.join(DELIMITER, API_PREFIX_HEADER, String.valueOf(id))).block();
    }

    public LaboratoryEmployeeDto getLaboratoryEmployee(Long id) {
        return getLaboratoryEmployee(String.join(DELIMITER, API_PREFIX_EMPLOYEE, String.valueOf(id))).block();
    }

    public List<MeasuringToolDto> getMeasuringTool(List<Long> ids) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("id", List.of(String.valueOf(ids)));
        return getAllMeasuringTools(String.join(DELIMITER, API_PREFIX_MEASURING), params).buffer().blockFirst();
    }

    public List<DocumentationDto> getDocumentations(List<Long> ids) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("id", List.of(String.valueOf(ids)));
        return getAllDocumentations(String.join(DELIMITER, API_PREFIX_DOCUMENTATION, "all"), params).buffer().blockFirst();
    }
}