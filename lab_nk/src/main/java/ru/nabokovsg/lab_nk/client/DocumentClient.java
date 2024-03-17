package ru.nabokovsg.lab_nk.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.lab_nk.dto.taskJournal.TasksJournalDataDto;

@Component
public class DocumentClient {

    private final WebClient client;

    @Autowired
    public DocumentClient(@Qualifier("webClientDocument") WebClient client) {
        this.client = client;
    }

    public void createDocumentData(String path, TasksJournalDataDto task) {
        client.post()
              .uri(path)
              .bodyValue(task);
    }
}