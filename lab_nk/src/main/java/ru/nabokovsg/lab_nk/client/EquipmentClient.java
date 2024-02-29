package ru.nabokovsg.lab_nk.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.lab_nk.client.dto.FullEquipmentDto;

@Component
public class EquipmentClient {

    private final WebClient client;

    @Autowired
    public EquipmentClient(@Qualifier("webClientEquipment") WebClient client) {
        this.client = client;
    }

    public FullEquipmentDto getEquipment(String path) {
        return client.get()
                .uri(path)
                .retrieve()
                .bodyToMono(FullEquipmentDto.class)
                .block();
    }
}