package ru.nabokovsg.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.result.models.PermissibleDeviationsGeodesy;

public interface PermissibleDeviationsGeodesyRepository extends JpaRepository<PermissibleDeviationsGeodesy, Long> {

    PermissibleDeviationsGeodesy findByEquipmentTypeIdAndFullAndOld(Long equipmentTypeId, Boolean full, Boolean old);
}