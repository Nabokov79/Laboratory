package ru.nabokovsg.lab_nk.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.lab_nk.dto.taskJournal.FullTaskJournalDto;

@Component
public class DocumentClient {

    private final WebClient client;

    @Autowired
    public DocumentClient(@Qualifier("webClientDocument") WebClient client) {
        this.client = client;
    }

    public Long createDocumentData(String path, FullTaskJournalDto taskJournalDto) {
        return client.post()
                .uri(path)
                .bodyValue(taskJournalDto)
                .retrieve()
                .bodyToFlux(Long.class)
                .blockFirst();
    }
}