package ru.nabokovsg.company.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.company.dto.address.AddressDto;
import ru.nabokovsg.company.dto.address.FullAddressDto;
import ru.nabokovsg.company.models.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address mapToAddress(AddressDto addressDto);

    FullAddressDto mapToFullAddressDto(Address address);
}