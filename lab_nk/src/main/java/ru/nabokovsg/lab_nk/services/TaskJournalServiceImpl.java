package ru.nabokovsg.lab_nk.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.lab_nk.mappers.TaskJournalMapper;
import ru.nabokovsg.lab_nk.repository.TaskJournalRepository;

@Service
@RequiredArgsConstructor
public class TaskJournalServiceImpl implements TaskJournalService {

    private final TaskJournalRepository repository;
    private final TaskJournalMapper mapper;
}