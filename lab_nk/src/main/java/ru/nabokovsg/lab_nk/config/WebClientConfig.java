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
}
