package ru.nabokovsg.template.client;

import lombok.AllArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.template.client.dto.*;

@AllArgsConstructor
public class Client {

    private WebClient client;

    public Mono<DivisionDto> getDivision(String path) {
        return client.get()
                .uri(path)
                .retrieve()
                .bodyToMono(DivisionDto.class);
    }

    public Mono<HeaderDocumentDto> getHeaderDocument(String path) {
        return client.get()
                .uri(path)
                .retrieve()
                .bodyToMono(HeaderDocumentDto.class);
    }

    public Mono<LaboratoryEmployeeDto> getLaboratoryEmployee(String path) {
        return client.get()
                .uri(path)
                .retrieve()
                .bodyToMono(LaboratoryEmployeeDto.class);
    }

    public Flux<MeasuringToolDto> getAllMeasuringTools(String path, MultiValueMap<String, String> params) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParams(params)
                        .build())
                .retrieve()
                .bodyToFlux(MeasuringToolDto.class);
    }

    public Flux<DocumentationDto> getAllDocumentations(String path, MultiValueMap<String, String> params) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParams(params)
                        .build())
                .retrieve()
                .bodyToFlux(DocumentationDto.class);
    }
}
