package ru.nabokovsg.lab_nk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${company-server.url}")
    private String companyUrl;

    @Value("${equipment-server.url}")
    private String equipmentUrl;

    @Value("${document-server.url}")
    private String documentUrl;

    @Value("${result-server.url}")
    private String resultUrl;

    @Bean
    public WebClient webClientCompany() {
        return WebClient.builder()
                .baseUrl(companyUrl)
                .build();
    }

    @Bean
    public WebClient webClientEquipment() {
        return WebClient.builder()
                .baseUrl(equipmentUrl)
                .build();
    }

    @Bean
    public WebClient webClientDocument() {
        return WebClient.builder()
                .baseUrl(documentUrl)
                .build();
    }

    @Bean
    public WebClient webClientResult() {
        return WebClient.builder()
                .baseUrl(resultUrl)
                .build();
    }
}
