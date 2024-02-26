package ru.nabokovsg.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.dto.equipment_service.equipmentPassport.NewEquipmentPassportDto;
import ru.nabokovsg.gateway.dto.equipment_service.equipmentPassport.UpdateEquipmentPassportDto;
import ru.nabokovsg.gateway.dto.equipment_service.equipments.NewEquipmentDto;
import ru.nabokovsg.gateway.dto.equipment_service.equipments.UpdateEquipmentDto;
import ru.nabokovsg.gateway.dto.equipment_service.elements.NewElementDto;
import ru.nabokovsg.gateway.dto.equipment_service.elements.UpdateElementDto;
import ru.nabokovsg.gateway.dto.equipment_service.partElement.NewPartElementDto;
import ru.nabokovsg.gateway.dto.equipment_service.partElement.UpdatePartElementDto;

@Service
public class EquipmentClient extends BaseClient {

    private static final String DELIMITER = "/";
    private static final String API_PREFIX_EQUIPMENT = "/equipments";
    private static final String API_PREFIX_EQUIPMENT_TYPE = "/equipments/type";
    private static final String API_PREFIX_ELEMENT = "/elements";
    private static final String API_PREFIX_PART_ELEMENT = "/parts";
    private static final String API_PREFIX_PASSPORT = "/passport";

    public EquipmentClient(@Value("${equipment-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> saveEquipment(NewEquipmentDto equipmentDto) {
        return post(API_PREFIX_EQUIPMENT, equipmentDto);
    }

    public Mono<Object> updateEquipment(UpdateEquipmentDto equipmentDto) {
        return patch(API_PREFIX_EQUIPMENT, equipmentDto);
    }

    public Mono<Object> getEquipment(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_EQUIPMENT, String.valueOf(id)));
    }

    public Flux<Object> getAllEquipment(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX_EQUIPMENT, "all", String.valueOf(id)));
    }

    public Mono<String> deleteEquipment(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_EQUIPMENT, String.valueOf(id)));
    }

    public Flux<Object> getAllEquipmentType() {
        return getAll(API_PREFIX_EQUIPMENT_TYPE);
    }

    public Mono<Object> saveElement(NewElementDto elementDto) {
        return post(API_PREFIX_ELEMENT, elementDto);
    }

    public Mono<Object> updateElement(UpdateElementDto elementDto) {
        return patch(API_PREFIX_ELEMENT, elementDto);
    }

    public Mono<Object> getElement(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_ELEMENT, String.valueOf(id)));
    }

    public Flux<Object> getAllElement(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX_ELEMENT, "all", String.valueOf(id)));
    }

    public Mono<String> deleteElement(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_ELEMENT, String.valueOf(id)));
    }

    public Mono<Object> savePartElement(NewPartElementDto partElementDto) {
        return post(API_PREFIX_PART_ELEMENT, partElementDto);
    }

    public Mono<Object> updatePartElement(UpdatePartElementDto partElementDto) {
        return patch(API_PREFIX_PART_ELEMENT, partElementDto);
    }

    public Mono<Object> getPartElement(Long id) {
        return get(String.join(DELIMITER, API_PREFIX_PART_ELEMENT, String.valueOf(id)));
    }

    public Flux<Object> getAllPartElement(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX_PART_ELEMENT, "all", String.valueOf(id)));
    }

    public Mono<String> deletePartElement(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_PART_ELEMENT, String.valueOf(id)));
    }

    public Mono<Object> saveEquipmentPassport(NewEquipmentPassportDto passportDto) {
        return post(API_PREFIX_PASSPORT, passportDto);
    }

    public Mono<Object> updateEquipmentPassport(UpdateEquipmentPassportDto passportDto) {
        return patch(API_PREFIX_PASSPORT, passportDto);
    }

    public Mono<String> deleteEquipmentPassport(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX_PASSPORT, String.valueOf(id)));
    }
}
