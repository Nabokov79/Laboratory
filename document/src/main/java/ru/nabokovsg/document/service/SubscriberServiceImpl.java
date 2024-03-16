package ru.nabokovsg.document.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.document.dto.document.LaboratoryEmployeeDto;
import ru.nabokovsg.document.mapper.SubscriberMapper;
import ru.nabokovsg.document.models.Subscriber;
import ru.nabokovsg.document.repository.SubscriberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberRepository repository;
    private final SubscriberMapper mapper;

    @Override
    public List<Subscriber> save(List<LaboratoryEmployeeDto> employees) {
        return repository.saveAll(employees.stream()
                         .map(e -> mapper.mapToSubscriber(e.getId(), e.getPost(), buildInitials(e)))
                         .toList());
    }

    @Override
    public Subscriber getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Subscriber with id=%s not found", id)));
    }

    private String buildInitials(LaboratoryEmployeeDto employee) {
        return String.join("/"
                , employee.getPost()
                , String.join(". ", String.join(".", String.valueOf(employee.getName().charAt(0))
                                        , String.valueOf(employee.getPatronymic().charAt(0)))
                                .toUpperCase()
                        , employee.getSurname()));
    }
}