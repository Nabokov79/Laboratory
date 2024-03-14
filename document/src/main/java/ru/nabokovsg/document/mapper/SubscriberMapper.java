package ru.nabokovsg.document.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.document.models.Subscriber;

@Mapper(componentModel = "spring")
public interface SubscriberMapper {

    Subscriber mapToSubscriber(Long employeeId, String post, String initials);
}
