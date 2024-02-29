package ru.nabokovsg.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.dto.labNk_service.NewCertificateDto;
import ru.nabokovsg.gateway.dto.labNk_service.UpdateCertificateDto;
import ru.nabokovsg.gateway.dto.labNk_service.documentation.NewDocumentationDto;
import ru.nabokovsg.gateway.dto.labNk_service.documentation.UpdateDocumentationDto;
import ru.nabokovsg.gateway.dto.labNk_service.headerDocument.NewHeaderDocumentDto;
import ru.nabokovsg.gateway.dto.labNk_service.headerDocument.UpdateHeaderDocumentDto;
import ru.nabokovsg.gateway.dto.labNk_service.measuringToll.NewMeasuringToolDto;
import ru.nabokovsg.gateway.dto.labNk_service.measuringToll.UpdateMeasuringToolDto;
import ru.nabokovsg.gateway.dto.labNk_service.remarks.NewRemarkDto;
import ru.nabokovsg.gateway.dto.labNk_service.remarks.UpdateRemarkDto;

import java.time.LocalDate;
import java.util.List;

@Service
public class LabNKClient extends BaseClient {

    private static final String DELIMITER = "/";
    private static final String API_PREFIX_CERTIFICATE = "/certificates";
    private static final String API_PREFIX_DOCUMENTATION = "/documentation";
    private static final String API_PREFIX_DOCUMENT = "/document";
    private static final String API_PREFIX_HEADER = "/document/header";
    private static final String API_PREFIX_EMPLOYEE = "/employee";
    private static final String API_PREFIX_MEASURING = "/measuring";
    private static final String API_PREFIX_REMARK = "/remark";
    private static final String API_PREFIX_JOURNAL = "/journal";

    public LabNKClient(@Value("${labNk-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> saveCertificate(NewCertificateDto certificateDto) {
        return post(API_PREFIX_CERTIFICATE, certificateDto);
    }

    public Mono<Object> updateCertificate(UpdateCertificateDto certificateDto) {
        return patch(API_PREFIX_CERTIFICATE, certificateDto);
    }

    public Flux<Object> getAllCertificate(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX_CERTIFICATE, "all", String.valueOf(id)));
    }

    public Mono<String> deleteCertificate(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_CERTIFICATE, String.valueOf(id)));
    }

    public Mono<Object> saveDocumentation(NewDocumentationDto documentationDto) {
        return post(API_PREFIX_DOCUMENTATION, documentationDto);
    }

    public Mono<Object> updateDocumentation(UpdateDocumentationDto documentationDto) {
        return patch(API_PREFIX_DOCUMENTATION, documentationDto);
    }

    public Flux<Object> getAllDocumentation(String number, String title) {
        return getAll(String.join(DELIMITER, API_PREFIX_DOCUMENTATION, "all")
                                                    , "number", number
                                                    , "title", title);
    }

    public Mono<String> deleteDocumentation(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_DOCUMENTATION, String.valueOf(id)));
    }

    public Mono<Object> saveHeaderDocument(NewHeaderDocumentDto headerDocumentDto) {
        return post(API_PREFIX_HEADER, headerDocumentDto);
    }

    public Mono<Object> updateHeaderDocument(UpdateHeaderDocumentDto headerDocumentDto) {
        return patch(API_PREFIX_HEADER, headerDocumentDto);
    }

    public Flux<Object> getAllHeaderDocument() {
        return getAll(String.join(DELIMITER, API_PREFIX_HEADER));
    }

    public Mono<String> deleteHeaderDocument(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_HEADER, String.valueOf(id)));
    }

    public Mono<Object> getDocument(Long id, String type) {
        return get(String.join(DELIMITER, API_PREFIX_DOCUMENT, String.valueOf(id)), "type", type);
    }

    public Flux<Object> getAllDocuments(Long equipmentId, Long addressId, LocalDate startDate, LocalDate endDate) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put(String.valueOf(equipmentId), List.of(String.valueOf(equipmentId)));
        params.put(String.valueOf(addressId), List.of(String.valueOf(addressId)));
        params.put(String.valueOf(startDate), List.of(String.valueOf(startDate)));
        params.put(String.valueOf(endDate), List.of(String.valueOf(endDate)));
        return getAll(String.join(DELIMITER, API_PREFIX_DOCUMENT, "all"), params);
    }

    public Flux<Object> copyLaboratoryEmployee(Long id, String divisionType) {
        return getAll(String.join(DELIMITER, API_PREFIX_EMPLOYEE, "copy", String.valueOf(id))
                                                                , "divisionType", divisionType);
    }

    public Mono<Object> getLaboratoryEmployee(Long id) {
        return get(String.join(DELIMITER,API_PREFIX_EMPLOYEE, String.valueOf(id)));
    }

    public Flux<Object> getAllLaboratoryEmployee() {
        return getAll(String.join(DELIMITER, API_PREFIX_EMPLOYEE, "all"));
    }

    public Mono<String> deleteLaboratoryEmployee(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_EMPLOYEE, String.valueOf(id)));
    }

    public Mono<Object> saveMeasuringTool(NewMeasuringToolDto measuringToolDto) {
        return post(API_PREFIX_MEASURING, measuringToolDto);
    }

    public Mono<Object> updateMeasuringTool(UpdateMeasuringToolDto measuringToolDto) {
        return patch(API_PREFIX_MEASURING, measuringToolDto);
    }

    public Flux<Object> getAllMeasuringTools(String toll, String model, String workNumber,
                                                                        LocalDate manufacturing, LocalDate exploitation,
                                                                        String manufacturer, String organization,
                                                                        String controlType, Long employeeId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put(toll, List.of(toll));
        params.put(model, List.of(model));
        params.put(workNumber, List.of(workNumber));
        params.put(String.valueOf(manufacturing), List.of(String.valueOf(manufacturing)));
        params.put(String.valueOf(exploitation), List.of(String.valueOf(exploitation)));
        params.put(manufacturer, List.of(manufacturer));
        params.put(organization, List.of(organization));
        params.put(controlType, List.of(controlType));
        params.put(String.valueOf(employeeId), List.of(String.valueOf(employeeId)));
        return getAll(String.join(DELIMITER, API_PREFIX_MEASURING), params);
    }

    public Mono<String> deleteMeasuringTool(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_MEASURING, String.valueOf(id)));
    }

    public Mono<Object> saveRemark(NewRemarkDto remarkDto) {
        return post(API_PREFIX_REMARK, remarkDto);
    }

    public Mono<Object> updateRemark(UpdateRemarkDto remarkDto) {
         return patch(API_PREFIX_REMARK, remarkDto);
    }

    public Flux<Object> getAllRemarks(Long id, Boolean inspector) {
        return getAll(String.join(DELIMITER, API_PREFIX_REMARK, "all", String.valueOf(id))
                                                        , "inspector", String.valueOf(inspector));
    }
}