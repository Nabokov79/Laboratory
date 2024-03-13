package ru.nabokovsg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.document.models.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
}