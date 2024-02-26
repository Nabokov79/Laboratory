package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.address.AddressDto;
import ru.nabokovsg.company.dto.address.FullAddressDto;
import ru.nabokovsg.company.models.Address;

import java.util.List;

public interface AddressService {

    FullAddressDto save(AddressDto addressDto);

    FullAddressDto update(AddressDto addressDto);

    Address get(Long id);

    List<FullAddressDto> getAll();

    String delete(Long id);
}