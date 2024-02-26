package ru.nabokovsg.company.services;

import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.company.dto.contacts.ContactDto;
import ru.nabokovsg.company.models.Contact;

@Validated
public interface ContactService {

    Contact save(ContactDto contactDto);

    Contact update(ContactDto contactDto);
}