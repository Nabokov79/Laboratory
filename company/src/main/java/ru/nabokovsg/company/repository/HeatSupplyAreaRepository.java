package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.company.models.HeatSupplyArea;

import java.util.Set;

public interface HeatSupplyAreaRepository extends JpaRepository<HeatSupplyArea, Long> {

    HeatSupplyArea findByFullName(String fullName);

    Set<HeatSupplyArea> findAllByBranchId(Long branchId);
}