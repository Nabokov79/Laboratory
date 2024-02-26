package ru.nabokovsg.company.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.contacts.ContactDto;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mappers.ContactMapper;
import ru.nabokovsg.company.models.Contact;
import ru.nabokovsg.company.repository.ContactRepository;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ContactMapper mapper;

    @Override
    public Contact save(ContactDto contactDto) {
        return repository.save(mapper.mapToContact(contactDto));
    }

    @Override
    public Contact update(ContactDto contactDto) {
        if (repository.existsById(contactDto.getId())) {
            return repository.save(mapper.mapToContact(contactDto));
        }
        throw new NotFoundException(String.format("Contacts with id=%s not found for update", contactDto.getId()));
    }
}