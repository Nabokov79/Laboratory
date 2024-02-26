package ru.nabokovsg.lab_nk.client;

import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.lab_nk.client.dto.ShortEmployeeDto;

import java.util.List;

@AllArgsConstructor
public class CompanyClient {

    private final WebClient client;

    public List<ShortEmployeeDto> getAll(String path, String paramName, String param) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam(paramName, param)
                        .build())
                .retrieve()
                .bodyToFlux(ShortEmployeeDto.class)
                .buffer()
                .blockFirst();
    }
}