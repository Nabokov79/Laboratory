package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.company.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}