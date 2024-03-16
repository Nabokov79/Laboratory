package ru.nabokovsg.company.services;

import ru.nabokovsg.company.dto.address.AddressDto;
import ru.nabokovsg.company.dto.address.ResponseAddressDto;
import ru.nabokovsg.company.models.Address;

import java.util.List;

public interface AddressService {

    ResponseAddressDto save(AddressDto addressDto);

    ResponseAddressDto update(AddressDto addressDto);

    Address get(Long id);

    List<ResponseAddressDto> getAll();

    String delete(Long id);
}