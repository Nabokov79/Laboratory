package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.company.dto.contacts.ContactDto;
import ru.nabokovsg.company.models.Contact;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact mapToContact(ContactDto contactDto);
}