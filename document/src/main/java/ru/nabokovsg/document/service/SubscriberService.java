package ru.nabokovsg.document.service;

import ru.nabokovsg.document.dto.LaboratoryEmployeeDto;
import ru.nabokovsg.document.models.Subscriber;

import java.util.List;

public interface SubscriberService {

    List<Subscriber> save( List<LaboratoryEmployeeDto> employees);

    Subscriber getById(Long id);
}