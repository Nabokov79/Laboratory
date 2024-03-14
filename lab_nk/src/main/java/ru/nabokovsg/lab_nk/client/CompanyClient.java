package ru.nabokovsg.lab_nk.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.lab_nk.client.dto.BranchDto;
import ru.nabokovsg.lab_nk.client.dto.ShortEmployeeDto;

import java.util.List;

@Component
public class CompanyClient {

    private final WebClient client;

    @Autowired
    public CompanyClient(@Qualifier("webClientCompany") WebClient client) {
        this.client = client;
    }

    public List<ShortEmployeeDto> getAllEmployees(String path, String paramName, String param) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam(paramName, param)
                        .build())
                .retrieve()
                .bodyToFlux(ShortEmployeeDto.class)
                .buffer()
                .blockFirst();
    }

    public BranchDto getBranch(String path) {
        return client.get()
                .uri(path)
                .retrieve()
                .bodyToMono(BranchDto.class)
                .block();
    }
}