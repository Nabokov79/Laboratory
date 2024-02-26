package ru.nabokovsg.lab_nk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.lab_nk.models.TasksJournal;

public interface TaskJournalRepository extends JpaRepository<TasksJournal, Long> {
}